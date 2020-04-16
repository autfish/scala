import java.sql.{Connection, DriverManager, Statement}

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

object C33SparkStreamingWordCount {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("StreamingWordCount").setMaster("local[2]")
    val context: StreamingContext = new StreamingContext(sparkConf, Seconds(1))

    //数据源1 socket 配合linux nc工具调试使用
    val lines: ReceiverInputDStream[String] = context.socketTextStream("localhost", 9999, StorageLevel.MEMORY_AND_DISK_SER)

    //数据源2 kafka
    val kafkaParams = Map[String, Object] (
      "bootstrap.servers" -> "192.168.0.1:9092,192.168.0.2:9092,192.168.0.3:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "boss",
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (true: java.lang.Boolean)
    )
    val topics = Array("boss")
    val stream = KafkaUtils.createDirectStream[String, String](
      context,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )
    val kafkaLines = stream.map(record => record.value())

    val wordCounts: DStream[(String, Int)] = lines.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)

    //结果写入数据库
    wordCounts.foreachRDD(rdd => {
      rdd.foreachPartition(insertToDB)
    })

    context.start()
    context.awaitTermination()
  }


  def insertToDB(records:Iterator[(String, Int)]) = {
    var conn: Connection = null
    var statement: Statement = null

    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8", "root", "root123456")
      records.foreach(t => {
        val word = t._1
        val count = t._2
        val sql = "insert into wordcount(word,cnt) values('" + word + "'," + count + ")"
        statement = conn.createStatement()
        statement.execute(sql)
      })
    } catch {
      case e:Exception => e.printStackTrace()
    } finally {
      if(statement != null)
        statement.close()
      if(conn != null)
        conn.close()
    }
  }
}

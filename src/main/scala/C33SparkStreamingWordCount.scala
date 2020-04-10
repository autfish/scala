import java.sql.{Connection, DriverManager, Statement}

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object C33SparkStreamingWordCount {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("StreamingWordCount").setMaster("local[2]")
    val context: StreamingContext = new StreamingContext(sparkConf, Seconds(1))

    //配合linux nc工具调试使用
    val lines: ReceiverInputDStream[String] = context.socketTextStream("localhost", 9999, StorageLevel.MEMORY_AND_DISK_SER)
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

import org.apache.spark.sql.streaming.{StreamingQuery, Trigger}
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object C32SparkStructuredStreaming {
  def main(args: Array[String]): Unit = {

    val session: SparkSession = SparkSession.builder()
      .master("local[2]").appName("StructuredNetworkWordCount").getOrCreate()

    //数据源1 socket 配合linux nc工具调试使用
    val lines: DataFrame = session.readStream.format("socket").option("host", "localhost").option("port", 9999).load()

    //数据源2 kafka
    import session.implicits._
    val dataFrame:DataFrame = session.readStream.format("kafka")
      .option("kafka.bootstrap.servers", "192.168.0.1:9092,192.168.0.2:9092,192.168.0.3:9092")
      .option("subscribe", "topic1")
      .load()
    val kafkaLines = dataFrame.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)").as[(String, String)]

    val words: Dataset[String] = lines.as[String].flatMap(_.split(" "))
    val wordCounts: DataFrame = words.groupBy("value").count().toDF("word", "count")
    val query: StreamingQuery = wordCounts
      .writeStream
      .outputMode("complete")
      .foreach(new JDBCSink("jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8", "root", "root123456"))
      .trigger(Trigger.ProcessingTime("2 seconds"))
      .start()
    query.awaitTermination()
  }
}

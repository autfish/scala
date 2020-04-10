import org.apache.spark.sql.streaming.StreamingQuery
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object C32SparkStructuredStreaming {
  def main(args: Array[String]): Unit = {
    val session: SparkSession = SparkSession.builder()
      .master("local[2]").appName("StructuredNetworkWordCount").getOrCreate()

    val lines: DataFrame = session.readStream.format("socket").option("host", "localhost").option("port", 9999).load()

    val words: Dataset[String] = lines.as[String].flatMap(_.split(" "))
    val wordCounts: DataFrame = words.groupBy("value").count()
    val query: StreamingQuery = wordCounts.writeStream.outputMode("complete").format("console").start()
    query.awaitTermination()
  }
}

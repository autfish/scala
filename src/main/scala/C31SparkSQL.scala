import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object C31SparkSQL {

  case class WordCount(word: String, count:Int)

  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder().appName("TopN").master("local[2]").getOrCreate()
    val dfRDD: RDD[(String, Int)] = getRDD(sparkSession)
    val dsRDD: RDD[WordCount] = getRDD(sparkSession).map(x => (WordCount(x._1, x._2)))
    dsRDD.foreach(println)

    import sparkSession.implicits._
    //DataFrame 类似于数据库的表, 每行一条数据
    val dataFrame: DataFrame = dfRDD.toDF()
    dataFrame.printSchema()
    dataFrame.select("_1", "_2").show()
    println("=====================================================")

    //Dataset 可以认为是DataFrame的一个特例, 存储的是类而不是Row
    val dataset: Dataset[WordCount] = dsRDD.toDS()
    dataset.createTempView("wordcount")
    sparkSession.sql("select word,count from wordcount order by count desc").show()
    println("=====================================================")

    //创建DataFrame并转换成RDD
    dataFrame2RDD(sparkSession).foreach(println)
    println("=====================================================")

    dataset2DataFrame(sparkSession)
    println("=====================================================")

    dataset2RDD(sparkSession)
  }

  def getRDD(sparkSession: SparkSession):RDD[(String, Int)] = {

    val value: RDD[(String, Int)] = sparkSession.sparkContext.textFile("src/test.txt").flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)
      .map(x => (x._2, x._1)).sortByKey(false).map(x => (x._2, x._1))
    value
  }

  def dataFrame2RDD(sparkSession: SparkSession) = {
    val frame: DataFrame = sparkSession.read.json("src/people.json")
    frame.show()
    frame.createTempView("people")
    sparkSession.sql("select name from people where age<30").rdd
  }

  def dataset2DataFrame(sparkSession: SparkSession): DataFrame = {
    import sparkSession.implicits._
    val frame: DataFrame = sparkSession.read.textFile("src/test.txt").flatMap(_.split(" ")).map((_, 1)).toDF("word", "count")
    frame.select("word", "count").groupBy("word").count().show()
    frame
  }

  def dataset2RDD(sparkSession: SparkSession) = {
    import sparkSession.implicits._
    val value: RDD[(String, Int)] = sparkSession.read.textFile("src/test.txt").rdd.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)
      .map(x => (x._2, x._1)).sortByKey(false).map(x => (x._2, x._1))
    value.foreach(println)
  }
}

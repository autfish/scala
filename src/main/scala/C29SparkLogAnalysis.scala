import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object C29SparkLogAnalysis {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("LogAnalysis").setMaster("local[2]")
    val sparkContext = new SparkContext(sparkConf)
    val data: RDD[String] = sparkContext.textFile("src/access.log")
    //pv
    println(data.count())
    val ipRDD: RDD[String] = data.map(line => line.split(" ")(0))
    //ip
    println(ipRDD.distinct().count())

    //访问量TOP N
    val validRDD: RDD[String] = data.filter(line => line.split(" ").length > 6).map(line => line.split(" ")(6))
    val urlAndOne: RDD[(String, Int)] = validRDD.map((_, 1))
    val result: RDD[(String, Int)] = urlAndOne.reduceByKey(_ + _)
    val top5: Array[(String, Int)] = result.sortBy(x => x._2, false).take(5)
    top5.foreach(println)
    sparkContext.stop()
  }
}

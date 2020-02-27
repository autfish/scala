import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object C30SparkIpLocation {

  def binarySearch(ipNum: Long, cityIpArray: Array[(String, String, String, String)]): Int = {
    var start = 0
    var end = cityIpArray.length - 1
    while(start <= end) {
      val middle = (start + end) / 2
      if(ipNum >= cityIpArray(middle)._1.toLong && ipNum <= cityIpArray(middle)._2.toLong)
        return middle

      if(ipNum < cityIpArray(middle)._1.toLong)
        end = middle - 1
      if(ipNum > cityIpArray(middle)._2.toLong)
        start = middle + 1
    }
    -1
  }

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("IpLocation").setMaster("local[2]")

    val sparkContext = new SparkContext(sparkConf)
    sparkContext.setLogLevel("warn")

    //IP表 获取IP开始数字, 结束数字, 精度, 纬度
    val cityIpRDD: RDD[(String, String, String, String)] = sparkContext.textFile("src/ip.txt").map(x => x.split("\\|")).map(x => (x(0), x(1), x(2), x(3)))

    //使用广播变量把共同数据发送到参与计算的每个Worker节点
    val cityIpBroadcast: Broadcast[Array[(String, String, String, String)]] = sparkContext.broadcast(cityIpRDD.collect())

    //读取日志中的IP
    val ipRDD: RDD[String] = sparkContext.textFile("src/access.log").map(line => line.split(" ")(0))

    //mapPartitions 一个函数只执行一次 一次接收Partition所有数据
    val result: RDD[((String, String), Int)] = ipRDD.mapPartitions(iter => {
      val cityIpArray: Array[(String, String, String, String)] = cityIpBroadcast.value
      iter.map(ip => {
        val ipNum: Long = ipToLong(ip)

        val index: Int = binarySearch(ipNum, cityIpArray)
        val result: (String, String, String, String) = cityIpArray(index)
        //((精度, 纬度), 1)
        ((result._3, result._4), 1)
      })
    })
    val finalResult: RDD[((String, String), Int)] = result.reduceByKey(_ + _)
    finalResult.foreach(println)

    sparkContext.stop()
  }

  def ipToLong(ip: String): Long = {
    val ips: Array[String] = ip.split("\\.")
    var ipNum:Long = 0L
    for(i <- ips) {
      ipNum = i.toLong | ipNum << 8L
    }
    ipNum
  }
}

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object C28SparkWordCount {
  def main(args: Array[String]): Unit = {
    //构建SparkConf对象 设置master地址 local[2]表示本地两个线程模拟
    val sparkConf = new SparkConf().setAppName("WordCount").setMaster("local[2]")

    //构建SparkContext对象
    val sparkContext = new SparkContext(sparkConf)

    //读取数据文件
    val data: RDD[String] = sparkContext.textFile("src/test.txt")

    //切分每一行获取所有的单词 flatMap: 先对每一条记录做split操作得到数组集合, 在压扁得到所有单词的集合
    val words: RDD[String] = data.flatMap(line => line.split(" "))

    //把每一个单词变为(word, 1)形式的元组
    val wordAndOne: RDD[(String, Int)] = words.map(word => (word, 1))

    //reduceByKey
    //先按key分组得到Apple -> List(1, 1, 1), Orange -> List(1, 1)
    //再对每个List执行reduce操作
    val result: RDD[(String, Int)] = wordAndOne.reduceByKey((v1, v2) => v1 + v2)

    //一行代码写法
    //sparkContext.textFile("src/test.txt").flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)

    //收集结果并打印
    val finalResult: Array[(String, Int)] = result.collect()
    finalResult.foreach(i => println(i))

    //关闭SparkContext
    sparkContext.stop()
  }
}

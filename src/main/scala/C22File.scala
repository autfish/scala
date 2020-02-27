import java.io.PrintWriter

import scala.io.Source

object C22File {
  def main(args: Array[String]): Unit = {
    //读取文件行
    val source1 = Source.fromFile("src/test.txt")
    //获取文件行迭代器
    val lines = source1.getLines()
    for(line <- lines) {
      println(line)
    }
    source1.close()

    //读取文件字符
    val source2 = Source.fromFile("src/test.txt")
    val iterator = source2.buffered
    while(iterator.hasNext) {
      println(iterator.head)
      println(iterator.next())
    }
    source2.close()

    val source3 = Source.fromURL("http://www.baidu.com")
    val lines1 = source3.getLines()
    for(line <- lines1) {
      println(line)
    }
    source3.close()

    var out = new PrintWriter("src/test.txt")
    out.println("Bat")
    out.close()
  }
}

import scala.util.matching.Regex

object C23Regex {
  def main(args: Array[String]): Unit = {
    var pattern1 = "[0-9]+".r
    var pattern2 = new Regex("[0-9]+")
    //如果正则表达式中含有\或引号, 用"""..."""
    var pattern3 = """\s+[0-9]"\s"""

    var matchStr = "99abc,100xyz"
    //返回所有匹配项
    var iterator = pattern1.findAllIn(matchStr)
    for(m <- iterator)
      println(m)

    //返回首个匹配项
    val first = pattern1.findFirstIn(matchStr)
    println(first.get)

    //检查字符串开始部分是否匹配
    val isStartMatch = pattern1.findPrefixMatchOf(matchStr)
    println(isStartMatch.get)

    //替换
    val res1 = pattern1.replaceFirstIn(matchStr, "xx")
    println(res1)
    val res2 = pattern1.replaceAllIn(matchStr, "xx")
    println(res2)
  }
}

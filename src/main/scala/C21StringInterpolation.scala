//字符串插值器
//s f raw
object C21StringInterpolation {
  def main(args: Array[String]): Unit = {
    //s
    val name = "Tom"
    val res1 = s"Hello, $name"
    println("res1=" + res1)

    val res2 = s"1 + 1 = ${1 + 1}"
    println("res2=" + res2)

    //f
    val height = 1.8d
    val res3 = f"$name is $height%2.2f meters high"
    println("res3=" + res3)

    //raw 类似s插值器, 但是不对其中的内容做转换
    val res4 = s"a\nb"
    val res5 = raw"a\nb"
    println("res4=" + res4)
    println("res5=" + res5)
  }
}

object C19PartialFunction {
  def main(args: Array[String]): Unit = {
    var x = 0
    if(div2.isDefinedAt(x))
      println(div2(x))
    else
      println(x + " is not allowed")

    println(div3.isDefinedAt(x))
    println(div3(2))

    println(res1.isDefinedAt(4))
    println(res2.isDefinedAt(4))
    println(res3(2))
  }

  var div2 = new PartialFunction[Int, Int] {
    override def isDefinedAt(x: Int): Boolean = x != 0

    override def apply(v1: Int): Int = 100 / v1
  }

  var div3:PartialFunction[Int, Int] = {
    case d:Int if(d != 0) => 100 / d
  }

  var res1:PartialFunction[Int, String] = {
    case 1 => "1"
    case 2 => "2"
    case 3 => "3"
  }

  //orElse 组合多个偏函数
  var r1:PartialFunction[Int, String] = { case 1 => "1" }
  var r2:PartialFunction[Int, String] = { case 2 => "2" }
  var r3:PartialFunction[Int, String] = { case 3 => "3" }
  var res2 = r1 orElse r2 orElse r3 //等价于res1

  //andThen 连接多个偏函数, 第一个函数的输出作为第二个函数的输入, 顺序执行
  var r4:PartialFunction[Int, Int] = { case i:Int => i + 1 }
  var r5:PartialFunction[Int, String] = { case i:Int => i.toString }
  var r6:PartialFunction[String, String] = { case s:String => "Output: " + s }
  var res3 = r4 andThen r5 andThen r6
}

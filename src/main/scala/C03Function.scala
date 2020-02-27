object C03Function {
  def main(args:Array[String]): Unit = {
    println("Hello world");
    //函数
    fun1(1, 2)
    fun2(1, 2)
    println(fun3(1, 2))
    println(fun4(1, 2))
    fun5()
  }

  val fun1 = (x:Int, y:Int) => {
    println(x + y)
  }

  val fun2 = ((x:Int, y:Int) => println(x + y))

  val fun3 = (_:Int) + (_:Int)

  val fun4 = new Function2[Int, Int, Int] {
    def apply(x:Int, y:Int) = x + y
  }

  val fun5 = () => println("Hello")
}

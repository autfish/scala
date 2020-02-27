//隐式类
object C26Implicit {
  //只能定义在object,class,trait内部
  implicit class IntWithTimes(x:Int) {
    def times[A](f: => A):Unit = {
      def loop(current:Int):Unit = {
        if(current > 0) {
          f
          loop(current - 1)
        }
      }
      loop(x)
    }
  }

  def main(args: Array[String]): Unit = {
    //如果在其他类中调用需要 import C26Implicit._
    10 times println("success")

    //隐式转换函数
    var a:Int = 10
    var b:Double = 100.99
    //报错 a = b
    //定义一个隐式转换函数
    implicit def doubleToInt(x:Double):Int = x.toInt
    a = b

    //隐式参数
    trait Adder[T] {
      def add(x:T, y:T):T
    }
    implicit val x = new Adder[Int] {
      override def add(x: Int, y: Int): Int = x + y
    }
    def addTest(x:Int, y:Int)(implicit adder:Adder[Int]):Int = {
      adder.add(x, y)
    }
    println(addTest(1, 2))
    println(addTest(1, 2)(x))

  }
}

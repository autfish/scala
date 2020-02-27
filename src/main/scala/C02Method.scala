
object C02Method {
  def main(args:Array[String]) {
    //方法
    //def 方法名(参数列表): 返回类型 = {}
    m1(1, 2)
    //带默认值参数的方法
    //def 方法名(参数名:参数类型 = 值) = {}
    m2()
    m2("Not Empty")
    //可变长参数方法
    m3(1, 2, 3, 4)
    //函数作为方法的参数
    println(m4(f))
    //方法转换为函数(1作为另一个方法的参数时 2使用空格+下划线把方法转换为函数)
    println(m4(m5))
    //方法嵌套
    println(fatorial(5))
    //多态
    println(listOfDuplicates[Int](1, 4))
    println(listOfDuplicates("s", 4))
  }

  def m1(x:Int, y:Int):Int = {
    x + y
  }

  def m2(str:String = "Empty") = println(str)

  def m3(x:Int*) = {
    for(i <- x) {
      println(x)
    }
  }

  def m4(f:(Int, Int) => Int) = f(5, 1)

  val f = (x:Int, y:Int) => x - y

  def m5(x:Int, y:Int) = x - y

  //方法的嵌套
  def fatorial(x:Int):Int = {
    def fact(x:Int, accumulator:Int):Int = {
      if(x <= 1) accumulator
      else fact(x - 1, x * accumulator)
    }
    fact(x, 1)
  }

  //多态 通过类型实现参数化(类似泛型)
  def listOfDuplicates[A](x:A, length:Int):List[A] = {
    if(length < 1) Nil
    else x::listOfDuplicates(x, length - 1)
  }
}



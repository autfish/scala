object C01Variable {

  //var 变量名 = 初始化值
  //var 变量名:数据类型 = 初始化值
  var int1 = 1
  var int2:Int = 2
  var str:String = "SSS"

  var array1 = Array(1, 2, 3)
  array1(0) = 4

  //val 常量
  val double1:Double = 10

  //延迟到使用时赋值
  lazy val array2 = Array(4, 5, 6)

  //值类型和引用类型

  //值类型相当于JAVA的包装类型, 没有基本数据类型
  //Double, Float, Long, Int, Short, Byte, Unit, Boolean, Char -> AnyVal -> Any

  //1是Int类型, 所以可以直接调用.to方法
  var array3 = 1.to(10)
  println(array3(3))

  //Unit相当于JAVA的Void, 用()表示

  //引用类型
  //List, Option, YourClass -> AnyRef(object) -> Any

  //表达式
  //表达式有返回值, 是表达式中最后一条语句的执行结果
  var x = 1
  val res1 = if(x > 0) 100 else "success"
  println(res1)
  val res2 = if(x < 0) 100
  println(res2)

  //块表达式 {一条或多条语句}
  val res3 = { 10 }
  var res4 = {
    val a = 10
    val b = 10
    a + b
  }
  println(res4)

  //for
  for(i <- 1 to 10) { //1-10
    print(i)
  }
  println("")
  for(i <- 1 until 10) { //1-9
    print(i)
  }
  println("")
  val s = "scala"
  for(i <- 0 until s.length) {
    print(s(i)) //scala
  }
  println("")
  for(i <- s) {
    print(i) //scala
  }
  println("")
  //嵌套循环
  for(i <- 1 to 3; j <- 1 to 3) {
    print(i)
    print(j)
    println("")
  }
  for(i <- 1 to 3; j <- 1 to 3 if(i != j)) {
    print(i)
    print(j)
    println("")
  }
}

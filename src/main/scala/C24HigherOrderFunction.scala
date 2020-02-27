//高阶函数
//函数的参数或返回值是函数
object C24HigherOrderFunction extends App{
  //传入参数是函数
  val arr = Array(1, 2, 3, 4, 5)
  val fun1 = (x:Int) => x * 2
  val res1 = arr.map(fun1)
  println(res1.toBuffer)
  //传入匿名函数
  val res2 = arr.map(x => x * 2)
  println(res2.toBuffer)

  //返回值是函数
  def urlBuilder(ssl:Boolean, domain:String):(String, String) => String = {
    val schema = if(ssl) "https://" else "http://"
    (endpoint:String, query:String) => s"$schema$domain/$endpoint?$query"
  }
  val fun2 = urlBuilder(true, "www.baidu.com")
  val url = fun2("user", "id=1")
  println(url)
}

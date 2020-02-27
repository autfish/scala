//object 单例对象
//不能带参数
//类似Java中工具类用法, 定义工具函数和常量
object C10Object {
  def log(msg:String): Unit = {
    println(s"INFO:$msg")
  }

  def main(args:Array[String]): Unit = {
    var test = new C10ObjectTest()
    test.test()
  }
}

class C10ObjectTest {
  def test(): Unit = {
    C10Object.log("hello")
  }
}
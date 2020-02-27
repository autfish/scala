//样例类

//case class 定义
//默认带有apply方法
//构造函数的参数默认是public val修饰
//样例类的比较基于属性值而不是引用
object C17CaseClass {
  def main(args: Array[String]): Unit = {
    var msg1 = Message("Tom", "Jerry") //apply方法
    //错误 msg1.sender = "John"

    var msg2 = Message("Tom", "Jerry")
    if(msg1 == msg2) println("TRUE")

    //样例类的拷贝
    var msg3 = msg1.copy()
    println("msg3.sender=" + msg3.sender)
    //不完全拷贝
    var msg4 = msg1.copy(sender = "Tom");
    println("msg4.sender=" + msg4.sender)
  }
}

case class Message(sender:String, receiver:String)

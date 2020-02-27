import scala.util.Random

object C18PatternMatching {
  def main(args: Array[String]): Unit = {
    //常量字面值匹配
    var animal1 = "TOM"
    animal1 match {
      case "TOM" => println("TRUE")
      case _ => println("FALSE")
    }
    //常量变量匹配 必须大写
    val TOM = "TOM"
    animal1 match {
      case TOM => println("TRUE")
      case _ => println("FALSE")
    }
    //错误示例
    val jerry = "JERRY"
    animal1 match {
      case jerry => println(jerry + " TRUE")
      case _ => println("FALSE")
    }
    //通配符模式匹配
    val list = List(1, 2, 3)
    list match {
      case List(_, _, 3) => println("TRUE")
      case _ => println("FALSE")
    }

    //case class匹配
    var email = new Email("Tom", "Hello");
    showNotification(email)
    var sms = new SMS("Jerry", "Hello")
    showNotification(sms)

    //类型匹配
    var arr = Array("s", 1, 2.3, 'c')
    var obj = arr(Random.nextInt(4))
    obj match {
      case i:Int => println("Int")
      case s:String => println("String")
      case d:Double => println("Double")
      case c:Char => println("Char")
      case _ => println("Not matched")
    }
  }

  def showNotification(notification: Notification) = {
    notification match {
      case Email(sender, title) => println("sender=" + sender + ", title=" + title)
      case SMS(sender, message) if sender == "Jerry" => println("sender=" + sender + ", message=" + message)
      case _ => println("unknown notification")
    }
  }
}

abstract class Notification
case class Email(sender:String, title:String) extends Notification
case class SMS(sender:String, message:String) extends Notification

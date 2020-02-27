class C12Apply(val name:String, val password:String) {

}

object C12Apply {
  //apply方法 构造一个对象 又叫注入器
  def apply(name:String, password:String) = new C12Apply(name, password)

  def unapply(arg: C12Apply): Option[(String, String)] = {
    if(arg == null) None
    else {
      Some(arg.name, arg.password)
    }
  }
}

object C12ApplyTest {
  def main(args: Array[String]): Unit = {
    var c12Apply1 = new C12Apply("zhangsna", "123456") //普通方式
    var c12Apply2 = C12Apply("zhangsan", "123456") //调用apply方法
    var isInstance = c12Apply2.isInstanceOf[C12Apply]
    println(isInstance)

    c12Apply2 match {
      case C12Apply(name, password) => println(name + ":" + password)
      case _ => println("Not a C12Apply")
    }
  }
}
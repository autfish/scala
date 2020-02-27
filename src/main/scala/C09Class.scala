class C09Class {
  //final 自动生成get方法
  val id:String = "123"

  //自动生成get/set方法
  var name:String = ""

  //自动生成private修饰的get/set方法
  private var gender:Int = 0

  //不会生成get/set方法
  private[this] var age:Int = 20

  println("默认构造函数")

  //辅助构造函数
  def this(name:String) {
    this() //必须调用主构造函数或者其他构造函数
    this.name = name
  }

  def getName():String = {
    println("custom method")
    this.name
  }
}

object C09ClassTest {
  def main(args:Array[String]): Unit = {
    var class1:C09Class = new C09Class()
    println(class1.id)
    class1.name = "zhangsan"
    println(class1.getName())

    var class2:C09Class = new C09Class("lisi")
    println(class2.name)
  }
}

class ConstructorDemo1(id:Int) {

}

class ConstructorDemo2 private(id:Int) {

}

//密封类
//不能在类文件以外定义子类
sealed class SealedClass {

}

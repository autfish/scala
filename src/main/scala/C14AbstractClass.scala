//特质还是抽象类
//优先使用特质
//需要带参数的构造方法只能使用抽象类
abstract class C14AbstractClass {
  //抽象字段
  var name:String //没有赋值
  //非抽象字段
  var level:Int = 10

  //抽象方法
  def getName():String
  //非抽象方法
  def getLevel():Int = {
    level
  }
}

class C14Class extends C14AbstractClass {
  var name = "Hello"

  def getName(): String = name

  override def getLevel(): Int = super.getLevel()
}
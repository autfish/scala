//final修改的类不能继承 属性或方法不能重写
class C13Extends(val xc:Int, val yc:Int) {
  var x:Int = xc
  var y:Int = yc

  def move(dx:Int, dy:Int): Unit = {
    x = x + dx
    y = y + dy
    println("move to " + x + "," + y)
  }
}

class Child(override val xc:Int, override val yc:Int, zc:Int) extends C13Extends(xc, yc) {
  var z:Int = zc

  def move(dx:Int, dy:Int, dz:Int): Unit = {
    x = x + dx;
    y = y + dy;
    z = z + dz;
    println("move to " + x + "," + y + "," + z)
  }
}

object C13ExtendsTest {
  def main(args: Array[String]): Unit = {
    var obj = new Child(5, 6, 7)
    obj.move(1, 2)
    obj.move(1, 2, 3)

    if(obj.isInstanceOf[C13Extends])
      println(obj.asInstanceOf[C13Extends])
    println(classOf[Child])
    println(obj.getClass)
  }
}

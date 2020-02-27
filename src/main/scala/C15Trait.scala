//带有抽象方法的特质
trait C15Trait[A] {
  def hasNext:Boolean
  def next:A
}

//带有实现的特质
trait C15TraitLogger {
  def log(msg:String) = {
    println(msg)
  }
}

class SomeClass(to:Int) extends C15Trait[Int] with C15TraitLogger {

  private var current = 0

  override def hasNext: Boolean = {
    current < to
  }

  override def next: Int = {
    if(hasNext) {
      var t = current
      current += 1
      t
    } else 0
  }
}

object TraitTest {
  def main(args: Array[String]): Unit = {
    var obj = new SomeClass(10)
    while(obj.hasNext) {
      obj.log(obj.next + "")
    }
  }
}

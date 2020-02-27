import scala.collection.mutable.Set

object C08Set {
  def main(args:Array[String]): Unit = {
    //不可变集合 不导入scala.collection.mutable.Set
    var set1 = Set(1, 2, 3)
    var set2 = set1 + 4

    //可变集合 导入scala.collection.mutable.Set
    val set3 = Set(4, 5, 6)
    set3 += 7
    println("set3=" + set3)
    println(set3.head)
    println(set3.tail)
    println(set3.max)

    var set4 = set3 ++ set2
    println("set4=" + set4)
    var set5 = set3.intersect(set2)
    println("set5=" + set5)
  }
}

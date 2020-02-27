import scala.collection.mutable.Map

object C06Map {
  def main(args:Array[String]): Unit = {
    //不可变Map 不导入scala.collection.mutable.Map
    var map1 = Map("a" -> 90, "b" -> 80)
    println("map1=" + map1)

    var map2 = Map(("a", 90), ("b", 80))
    println("map2=" + map2)

    //可变Map 导入scala.collection.mutable.Map

    println(map1("a"));
    if(map1.contains("b"))
      println(map1("b"));
    println(map1.getOrElse("c", 60));
    for(k <- map1.keys) {
      println("k=" + k)
    }
    for((k, v) <- map1) {
      println(k + "=" + v)
    }

    map1("a") = 80
    map1("c") = 70 //不存在时添加
    map1 += ("d" -> 30)
    map1 += ("a" -> 30)
    map1 -= "b"
    println("map1=" + map1)
  }
}

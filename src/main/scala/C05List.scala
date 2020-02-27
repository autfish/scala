import scala.collection.mutable.ListBuffer

object C05List {

  def main(args:Array[String]): Unit = {
    //不可变List
    var list1 = List(1, 2, 3)
    println(list1.getClass.getName)

    list1 :+ 4 //不会改变list1
    println("list1=" + list1)
    var list2 = 0 :: list1 //向集合的头部加入元素
    println("list2=" + list2)
    list2 = list2 :+ 5 //向集合的尾部加入元素
    println("list2=" + list2)
    list2 = 0 +: list2
    println("list2=" + list2) ////向集合的头部加入元素

    //可变List
    var list3 = ListBuffer(1, 2, 3)
    list3 += 4
    list3.append(5)
    println("list3=" + list3)

    var list4 = list2 ++ list3
    println("list4=" + list4)

    //求和
    list3.sum
    //最小值
    list3.min
    //最大值
    list3.max

    //map
    var list5 = list4.map(v => v * 10)
    println("list5=" + list5)
    var list6 = list4.map{v => print(v); v * 10}
    println
    println("list6=" + list6)

    //flatten 压平
    var list7 = List(list5, list6)
    println("list7=" + list7)
    var list8 = list7.flatten
    println("list8=" + list8)

    //flatMap  map+压平
    var list9 = List('a', 'b', 'c')
    var list10 = list9.map(ch => List(ch, ch.toUpper)).flatten
    println("list10=" + list10)
    var list11 = list9.flatMap(ch => List(ch, ch.toUpper)) //等价
    println("list11=" + list11)
    var list12 = list10 ::: list11  //只能用于连接两个List集合

    //forall
    var b1 = list1.forall(e => e > 0)
    var b2 = list2.forall(e => e > 0)
    println(b1)
    println(b2)

    //foreach
    list11.foreach(print)
    println("")

    //reduceLeft, reduceRight, foldLeft, foldRight
    var r1 = list2.reduceLeft(_ * 2 + _)
    //第一步0 * 2 + 1
    //第二步 (第一步结果)1 * 2 + 2
    //第三步 (第二步结果)4 * 2 + 3
    println(r1)
    var r2 = list2.foldLeft(5)(_ * 2 + _)
    //第一步5 * 2 + 0
    //第二步 (第一步结果)10 * 2 + 1
    //第三步 (第二步结果)21 * 2 + 2
    //第三步 (第三步结果)44 * 2 + 3
    println(r2)
  }

}

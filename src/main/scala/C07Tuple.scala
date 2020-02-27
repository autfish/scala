object C07Tuple {
  def main(args:Array[String]): Unit = {
    //元组
    var tuple1 = (1, 2, 3, 4)
    var tuple2 = (10, "java", true)
    println(tuple2._1)
    println(tuple2._2)
    //错误 tuple2._1 = 11 不能修改

    var tuple3,(key1, key2, key3) = (1, 2, 3)
    println(key1)

    var tuple4 = new Tuple4(10, "java", true, 1.5)
    tuple4.productIterator.foreach(i => print(i))
    println("")
    tuple4.productIterator.foreach(print(_))
    println("")

    //元组数组
    var array1 = Array("Java", "Python", "Scala")
    var array2 = Array(4, 5, 6)
    var array3 = array1.zip(array2)
    for(i <- array3) {
      print(i)
    }
  }
}

import scala.collection.mutable.ArrayBuffer

object C04Array {
  def main(args:Array[String]): Unit = {
    var array1 = Array[Int](1, 2, 3)
    var array2 = new Array[Int](10) //size 10

    var sum = array1.sum
    println("sum=" + sum)
    var max = array1.max
    println("max=" + max)

    array1 = array1.sorted.reverse
    for(elem <- array1) {
      print(elem)
    }
    println("")

    //变长数组
    var array3 = ArrayBuffer("a", "b", "c")
    var array4 = new ArrayBuffer[Int]()
    println("array4.length=" + array4.length)
    array4 += 4
    array4 += (5, 6)
    for(elem <- array4) {
      print(elem)
    }
    println("")

    array4 = array4 ++= array1
    array4.append(7)
    array4.insert(0, 55)
    array4 -= 1
    array4.trimEnd(1)
    array4.remove(0, 2)
    print("array4=")
    for(elem <- array4) {
      print(elem + ",")
    }
    println("")

    var array5 = for(elem <- array4) yield elem * 10
    print("array5=")
    for(elem <- array5) {
      print(elem + ",")
    }
    println("")

    var array6 = array5.map(_ * 10) //x => x * 10
    print("array6=")
    for(elem <- array6) {
      print(elem + ",")
    }
    println("")

    var array7 = array4.filter(_ % 2 == 0)
    print("array7=")
    for(elem <- array7) {
      print(elem + ",")
    }
    println("")
  }

}

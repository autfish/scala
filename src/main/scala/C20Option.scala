object C20Option {
  def div(x:Int, y:Int):Option[Int] = {
    if(y == 0) None
    else Some(x / y)
  }

  def main(args: Array[String]): Unit = {
    div(100, 0) match {
      case None => println("error")
      case Some(v) => println(v)
    }
  }
}

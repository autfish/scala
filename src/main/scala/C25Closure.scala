object C25Closure extends App {
  var factor = 0
  val multiply = (x:Int) => {
    factor = factor + 10
    x * factor
  }
  println("factor=" + factor)
  val res = multiply(2)
  println(res)
  println("factor=" + factor)
}

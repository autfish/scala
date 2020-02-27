//特质为类提供可以堆叠的改变
object C16TraitAdv {
  def main(args: Array[String]): Unit = {
    var acc1 = new SavingAccount(0)
    acc1.withdraw(100)
    var acc2 = new SavingAccount(0) with ShorterLogger
    acc2.withdraw(100)
    var acc3 = new SavingAccount(0) with TimestampLogger
    acc3.withdraw(100)
    var acc4 = new SavingAccount(0) with TimestampLogger with ShorterLogger
    acc4.withdraw(100)
    var acc5 = new SavingAccount(0) with ShorterLogger with TimestampLogger
    acc5.withdraw(100)

    var acc6 = new SavingAccount(101)
    acc6.withdraw(100)
  }
}

trait Logger {
  def log(msg:String)
  def info(msg:String) = log("INFO: " + msg)
}

trait ConsoleLogger extends Logger {
  override def log(msg: String): Unit = println(msg)
}

trait TimestampLogger extends ConsoleLogger {
  override def log(msg: String): Unit = super.log(msg = s"${java.time.Instant.now()} $msg")
}

trait ShorterLogger extends ConsoleLogger {
  val maxLength = 15
  override def log(msg: String): Unit = super.log(
    if(msg.length <= maxLength) msg
    else s"${msg.substring(0, maxLength)}..."
  )
}

class Account(val init:Double) {
  protected var balance:Double = init
}

class SavingAccount(override val init:Double) extends Account(init) with ConsoleLogger {
  def withdraw(amount:Double) = {
    if(amount > balance) log("Insufficient funds")
    else {
      balance = balance - amount
      info("Success")
    }
  }
}

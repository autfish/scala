import java.sql.{Connection, DriverManager}
import java.util

class MySQLPool(url:String, user:String, pwd:String) extends Serializable {
  private val max = 5
  private var conNum = 0
  private val pool = new util.LinkedList[Connection]()

  def getJdbcConn():Connection = {
    AnyRef.synchronized({
      if(pool.isEmpty) {
        preGetConn()
        val conn = DriverManager.getConnection(url, user, pwd)
        pool.push(conn)
        conNum += 1
      }
      pool.poll()
    })
  }

  def preGetConn():Unit = {
    if(conNum < max && !pool.isEmpty) {
      Thread.sleep(2000)
      preGetConn()
    } else {
      Class.forName("com.mysql.jdbc.driver")
    }
  }

  def releaseConn(conn:Connection):Unit = {
    pool.push(conn)
  }
}

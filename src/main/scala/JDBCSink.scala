import java.sql.{Connection, DriverManager, Statement}

import org.apache.spark.sql.{ForeachWriter, Row}

class JDBCSink(url:String, user:String, pwd:String) extends ForeachWriter[Row]{
  val driver = "com.mysql.jdbc.Driver"
  var connection:Connection = _
  var statement:Statement = _

  override def open(partitionId: Long, epochId: Long): Boolean = {
    Class.forName(driver)
    connection = DriverManager.getConnection(url, user, pwd)
    statement = connection.createStatement()
    true
  }

  override def process(value: Row): Unit = {
    val word = value.getAs[String]("word")
    val count = value.getAs[Int]("count")
    statement.executeUpdate("INSERT INTO test VALUES('" + word + "'," + count + ")")
  }

  override def close(errorOrNull: Throwable): Unit = {
    connection.close()
  }
}

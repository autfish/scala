import scala.actors.Actor

object C27Actor {
  def main(args: Array[String]): Unit = {
    val actor = new FirstActor
    //启动actor
    actor.start()
    //给actor发送消息 ! 发送异步消息没有返回值  !? 发送同步消息阻塞等待返回  !! 发送异步消息返回Future[Any]
    for (1 <- 1 to 3) {
      actor ! "start"
      actor ! "stop"
    }
  }
}

class FirstActor extends Actor {
  override def act(): Unit = {
    while(true) {
      receive {
        case "start" => {
          println("starting")
          Thread.sleep(1000)
          println("started")
        }
        case "stop" => {
          println("stopping")
          Thread.sleep(1000)
          println("stopped")
        }
      }
    }
  }
}

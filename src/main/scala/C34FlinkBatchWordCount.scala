import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.api.scala._

object C34FlinkBatchWordCount {
  def main(args: Array[String]): Unit = {
    val params: ParameterTool = ParameterTool.fromArgs(args)

    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment

    env.getConfig.setGlobalJobParameters(params)

    val text: DataSet[String] = env.readTextFile("src/test.txt")

    val counts = text.flatMap { _.toLowerCase.split("\\W+") filter { _.nonEmpty } }
      .map ( (_, 1) )
      .groupBy(0)
      .sum(1)

    counts.print()
  }
}

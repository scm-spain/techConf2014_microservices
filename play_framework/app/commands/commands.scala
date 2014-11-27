package commands

import com.netflix.hystrix.HystrixCommand.Setter
import com.netflix.hystrix.{HystrixCommandKey, HystrixCommandProperties, HystrixCommandGroupKey, HystrixCommand}





















object MicroServiceACommand {
  private final def key = Setter
    .withGroupKey(HystrixCommandGroupKey.Factory.asKey("microservices"))
    .andCommandKey(HystrixCommandKey.Factory.asKey("A"))

  def apply() = new MicroServiceACommand
}

class MicroServiceACommand extends HystrixCommand[Int](MicroServiceACommand.key) with BogusHelper {

  def run(): Int = {
    simulateNetworkLatency(50, 125)
    simulateFailUnderLoad(0.01)
    simulateNetworkSpike(15, 650, 2250)

    between(20, 35)
  }

  override def getFallback: Int = 0
}

object MicroServiceBCommand {
  private final def key = Setter
    .withGroupKey(HystrixCommandGroupKey.Factory.asKey("microservices"))
    .andCommandKey(HystrixCommandKey.Factory.asKey("B"))

  def apply() = new MicroServiceBCommand
}

class MicroServiceBCommand extends HystrixCommand[Int](MicroServiceBCommand.key) with BogusHelper {

  def run(): Int = {
    simulateNetworkLatency(50, 125)
    simulateFailUnderLoad(0.01)
    simulateNetworkSpike(15, 650, 2250)

    between(20, 35)
  }

  override def getFallback: Int = 0
}
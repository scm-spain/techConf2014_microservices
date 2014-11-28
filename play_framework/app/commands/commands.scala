package commands

import com.google.common.collect.Lists
import com.netflix.hystrix.HystrixCommand.Setter
import com.netflix.hystrix.{HystrixCommand, HystrixCommandGroupKey, HystrixCommandKey}
import com.netflix.loadbalancer.Server
import controllers.RibbonCommand
import play.api.libs.json.{JsValue, Json}

object MicroServiceACommand {
  private final def key = Setter
    .withGroupKey(HystrixCommandGroupKey.Factory.asKey("microservices"))
    .andCommandKey(HystrixCommandKey.Factory.asKey("MicroServiceA"))

  def apply() = new MicroServiceACommand
}

class MicroServiceACommand extends HystrixCommand[Int](MicroServiceACommand.key) with BogusHelper {

  def run(): Int = {
    //simulateNetworkLatency(50, 125)
    simulateFailUnderLoad(0.01)
    //simulateNetworkSpike(15, 650, 2250)
    val ribbon = new RibbonCommand(Lists.newArrayList(new Server("localhost", 8888)))
    val responseContent = ribbon.call("/friends/")
    val json: JsValue = Json.parse(responseContent)
    val friends2 = (json \ "friends").as[List[String]]
    between(20, 35)
  }

  override def getFallback: Int = 0
}

object MicroServiceBCommand {
  private final def key = Setter
    .withGroupKey(HystrixCommandGroupKey.Factory.asKey("microservices"))
    .andCommandKey(HystrixCommandKey.Factory.asKey("MicroServiceB"))

  def apply() = new MicroServiceBCommand
}

class MicroServiceBCommand extends HystrixCommand[List[String]](MicroServiceBCommand.key) with BogusHelper {

  def run(): List[String] = {
    //simulateNetworkLatency(50, 125)
    simulateFailUnderLoad(0.01)
    //simulateNetworkSpike(15, 650, 2250)
    val ribbon = new RibbonCommand(Lists.newArrayList(new Server("localhost", 8888)))
    val responseContent = ribbon.call("/friends/Chuck-norris")
    val json: JsValue = Json.parse(responseContent)
    val friends2 = (json \ "friends").as[List[String]]
    friends2
  }

  override def getFallback: List[String] = List("No friends")
}
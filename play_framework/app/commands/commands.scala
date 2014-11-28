package commands

import com.google.common.collect.Lists
import com.netflix.hystrix.HystrixCommand.Setter
import com.netflix.hystrix.{HystrixCommand, HystrixCommandGroupKey, HystrixCommandKey}
import com.netflix.loadbalancer.Server
import controllers.RibbonCommand
import models.User
import play.api.libs.json.{JsValue, Json}

object MicroServiceACommand {
  private final def key = Setter
    .withGroupKey(HystrixCommandGroupKey.Factory.asKey("microservices"))
    .andCommandKey(HystrixCommandKey.Factory.asKey("MicroServiceA"))

  def apply() = new MicroServiceACommand
}

class MicroServiceACommand extends HystrixCommand[User](MicroServiceACommand.key) with BogusHelper {

  def run(): User = {
    simulateNetworkLatency(500, 2000)
    simulateFailUnderLoad(0.01)
    simulateNetworkSpike(15, 650, 2250)
    val ribbon = new RibbonCommand(Lists.newArrayList(new Server("localhost", 8887)))
    val responseContent = ribbon.call("/user/4")
    val json: JsValue = Json.parse(responseContent)

    val userJson = (json \ "user")
    val gender = (json \ "user" \ "gender").as[String];
    val name = (json \ "user" \ "name").as[String];
    val surname = (json \ "user" \ "surname").as[String];
    val email = (json \ "user" \ "email").as[String];

    new User(gender, name, surname, email)
  }

  override def getFallback: User = new User("", "", "", "")
}

object MicroServiceBCommand {
  private final def key = Setter
    .withGroupKey(HystrixCommandGroupKey.Factory.asKey("microservices"))
    .andCommandKey(HystrixCommandKey.Factory.asKey("MicroServiceB"))

  def apply() = new MicroServiceBCommand
}

class MicroServiceBCommand extends HystrixCommand[List[String]](MicroServiceBCommand.key) with BogusHelper {

  def run(): List[String] = {
    simulateNetworkLatency(500, 1250)
    simulateFailUnderLoad(0.01)
    simulateNetworkSpike(15, 650, 2250)
    val ribbon = new RibbonCommand(Lists.newArrayList(new Server("localhost", 8888)))
    val responseContent = ribbon.call("/friends/Chuck-norris")
    val json: JsValue = Json.parse(responseContent)
    val friends2 = (json \ "friends").as[List[String]]
    friends2
  }

  override def getFallback: List[String] = List();
}
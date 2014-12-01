package commands

import com.google.common.collect.Lists
import com.netflix.hystrix.HystrixCommand.Setter
import com.netflix.hystrix.{HystrixCommand, HystrixCommandGroupKey, HystrixCommandKey}
import com.netflix.loadbalancer.Server
import controllers.RibbonCommand
import models.User
import play.api.libs.json.{JsValue, Json}

object MicroServiceUserCommand {
  private final def key = Setter
    .withGroupKey(HystrixCommandGroupKey.Factory.asKey("microservices"))
    .andCommandKey(HystrixCommandKey.Factory.asKey("MicroServiceUser"))

  def apply() = new MicroServiceUserCommand
}

class MicroServiceUserCommand extends HystrixCommand[User](MicroServiceUserCommand.key) with BogusHelper {

  def run(): User = {
    simulateNetworkLatency(500, 2000)
    simulateFailUnderLoad(0.01)
    simulateNetworkSpike(15, 650, 2250)
    val host = "localhost"; //set your microservice host here
    val port = 8887; //set your microservice port here
    val action = "user/4" //set you action, for example /user/5
    val queryString = ""; //set your query string, for example add=1&add=2
    val verb = "GET";
    val ribbon = new RibbonCommand(Lists.newArrayList(new Server(host, port)))
    val responseContent = ribbon.call(action, queryString, verb)
    val json: JsValue = Json.parse(responseContent)

    val userJson = (json \ "user")
    val gender = (json \ "user" \ "gender").as[String];
    val name = (json \ "user" \ "name").as[String];
    val surname = (json \ "user" \ "surname").as[String];
    val email = (json \ "user" \ "email").as[String];

    new User(gender, name, surname, email)
  }

  override def getFallback: User = new User("None", "none", "none", "none")
}

object MicroServiceFriendsCommand {
  private final def key = Setter
    .withGroupKey(HystrixCommandGroupKey.Factory.asKey("microservices"))
    .andCommandKey(HystrixCommandKey.Factory.asKey("MicroServiceFriends"))

  def apply() = new MicroServiceFriendsCommand
}


class MicroServiceFriendsCommand extends HystrixCommand[List[String]](MicroServiceFriendsCommand.key) with BogusHelper {

  def run(): List[String] = {
    //simulateNetworkLatency(500, 1250)
    //simulateFailUnderLoad(0.01)
    //simulateNetworkSpike(15, 650, 2250)
    val host = "localhost"; //set your microservice host here
    val port =8888; //set your microservice port here
    val action = "friends/Chuck-norris" //set you action, for example /user/5
    val queryString = ""; //set your query string, for example add=1&add=2
    val verb = "GET";
    val ribbon = new RibbonCommand(Lists.newArrayList(new Server(host, port)))
    val responseContent = ribbon.call(action, queryString, verb)
    val json: JsValue = Json.parse(responseContent)
    val friends2 = (json \ "friends").as[List[String]]
    friends2
  }

  override def getFallback: List[String] = List();
}


object MicroServiceAddCommand {
  private final def key = Setter
    .withGroupKey(HystrixCommandGroupKey.Factory.asKey("microservices"))
    .andCommandKey(HystrixCommandKey.Factory.asKey("MicroServiceAdd"))

  def apply() = new MicroServiceAddCommand
}

class MicroServiceAddCommand extends HystrixCommand[Long](MicroServiceAddCommand.key) with BogusHelper {

  //This is the code that will run when
  def run(): Long = {
    //simulateNetworkLatency(500, 1250)
    //simulateFailUnderLoad(0.01)
    //simulateNetworkSpike(15, 650, 2250)

    val host = "localhost"; //set your microservice host here
    val port = 8888; //set your microservice port here
    val action = "add" //set you action, for example /user/5
    val queryString = "addend1=1&addend2=2"; //set your query string, for example add=1&add=2
    val verb = "GET";

    val ribbon = new RibbonCommand(Lists.newArrayList(new Server(host, port)))
    val responseContent = ribbon.call(action, queryString, verb)
    val json: JsValue = Json.parse(responseContent)
    val result = (json \ "result").asOpt[Long]
    result.get
  }

  override def getFallback: Long= 0;
}
package commands

import com.google.common.collect.Lists
import com.netflix.hystrix.HystrixCommand.Setter
import com.netflix.hystrix.{HystrixCommand, HystrixCommandGroupKey, HystrixCommandKey}
import com.netflix.loadbalancer.Server
import play.api.libs.json.{JsValue, Json}


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

    val host = "????"; //set your microservice host here
    val port = 0; //set your microservice port here
    val action = "???" //set you action, for example user/5
    val queryString = "???"; //set your query string, for example add=1&add=2
    val verb = "????";

    val ribbon = new RibbonCommand(Lists.newArrayList(new Server(host, port)))

    //val ribbon = new EurekaRibbonCommand("your microservice_name");
    val responseContent = ribbon.call(action, queryString, verb)
    val json: JsValue = Json.parse(responseContent)
    val result = (json \ "result").asOpt[Long]
    result.get
  }

  override def getFallback: Long= 0;
}
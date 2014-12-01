package controllers

import commands.MicroServiceAddCommand
import play.api.mvc.{AnyContent, Request}
import views.html.microserviceadd.microServiceAdd

object MicroServiceAdd extends CommandStreamController {
  def pagelets(request: Request[AnyContent]) = {
    val microserviceAdd = command(MicroServiceAddCommand(), microServiceAdd.apply, "microserviceAdd")
    List(microserviceAdd)
  }

  val layout = views.pagelet.microserviceadd.streamed
}

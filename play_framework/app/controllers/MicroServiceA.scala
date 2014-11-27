package controllers

import commands.MicroServiceACommand
import play.api.mvc.{AnyContent, Request}
import views.html.microServiceA.microServiceA

object MicroServiceA extends CommandStreamController {

  def pagelets(request: Request[AnyContent]) = {
    val microserviceA = command(MicroServiceACommand(), microServiceA.apply, "microserviceA")
    List(microserviceA)
  }

  val layout = views.pagelet.microServiceA.streamed
}
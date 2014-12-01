package controllers

import play.api.mvc.{AnyContent, Request}
import views.html.microserviceuser.microServiceUser

object MicroServiceUser extends CommandStreamController {

  def pagelets(request: Request[AnyContent]) = {
    val microserviceUser = command(???, microServiceUser.apply, "microserviceUser")
    List(microserviceUser)
  }

  val layout = views.pagelet.microserviceuser.streamed
}
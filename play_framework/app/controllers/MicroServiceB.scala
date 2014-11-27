package controllers

import commands.MicroServiceBCommand
import play.api.mvc.{AnyContent, Request}
import views.html.microServiceB.microServiceB

object MicroServiceB extends CommandStreamController {

   def pagelets(request: Request[AnyContent]) = {
     val microserviceB = command(MicroServiceBCommand(), microServiceB.apply, "microserviceB")
     List(microserviceB)
   }

   val layout = views.pagelet.microServiceB.streamed
 }
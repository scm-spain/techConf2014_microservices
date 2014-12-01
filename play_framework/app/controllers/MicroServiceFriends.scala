package controllers

import commands.MicroServiceFriendsCommand
import play.api.mvc.{AnyContent, Request}
import views.html.microservicefriends.microServiceFriends

object MicroServiceFriends extends CommandStreamController {

   def pagelets(request: Request[AnyContent]) = {
     val microserviceFriend = command(MicroServiceFriendsCommand(), microServiceFriends.apply, "microserviceFriends")
     List(microserviceFriend)
   }

   val layout = views.pagelet.microservicefriends.streamed
 }
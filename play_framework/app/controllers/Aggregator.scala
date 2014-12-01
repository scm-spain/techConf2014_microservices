package controllers

import play.api.mvc.{AnyContent, Request}


object Aggregator extends CommandStreamController {

  def pagelets(request: Request[AnyContent]) =
      MicroServiceUser.pagelets(request) ::: MicroServiceFriends.pagelets(request) ::: MicroServiceAdd.pagelets(request)

  val layout = views.pagelet.aggregated.main
}

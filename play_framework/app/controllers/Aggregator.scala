package controllers

import play.api.mvc.{AnyContent, Request}


object Aggregator extends CommandStreamController {

  def pagelets(request: Request[AnyContent]) =
      MicroServiceA.pagelets(request) ::: MicroServiceB.pagelets(request)

  val layout = views.pagelet.aggregated.main
}

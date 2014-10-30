package controllers

import play.api.mvc.{Action, Controller}
import provider.PagingProvider

import scala.concurrent.Future

class PagingController(pagingService: PagingProvider) extends Controller {

  def action = Action {
    Ok("Blub")
  }

  def getProducts(page: Int, size: Int) = Action.async {
    Future.successful(Ok("test"))
  }
}

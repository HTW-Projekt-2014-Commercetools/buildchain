package controllers

import play.api.mvc.{Action, Controller}
import provider.PagingProvider
import scala.concurrent.ExecutionContext.Implicits.global

class PagingController(pagingProvider: PagingProvider) extends Controller {

  def showProducts = Action {
    Ok("Blub")
  }

  def getProducts(page: Int, size: Int) = Action.async {
    pagingProvider.getProducts(page, size).map(Ok(_))
  }
}

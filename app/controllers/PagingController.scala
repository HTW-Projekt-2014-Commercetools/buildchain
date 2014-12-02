package controllers

import play.api.mvc.{Action, Controller}
import services.PagingService
import scala.concurrent.ExecutionContext.Implicits.global

class PagingController(pagingProvider: PagingService) extends Controller {

  def showProducts = Action {
    Ok(views.html.index("Blub"))
  }

  def getProducts(page: Int, size: Int) = Action.async {
    pagingProvider.getProducts(page, size).map(Ok(_))
  }
}

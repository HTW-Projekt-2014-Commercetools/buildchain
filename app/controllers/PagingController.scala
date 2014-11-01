package controllers

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import provider.PagingProvider
import scala.concurrent.ExecutionContext.Implicits.global

class PagingController(pagingProvider: PagingProvider) extends Controller {

  def action = Action {
    Ok("Blub")
  }

  def getProducts(page: Int, size: Int) = Action.async {
    pagingProvider.getProducts(0, 5).map(Ok(_))
  }
}

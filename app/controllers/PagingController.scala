package controllers

import play.api.mvc.{Action, Controller}
import provider.PagingProvider

class PagingController(pagingService: PagingProvider) extends Controller {

  def action = Action {
    Ok("Blub")
  }
}

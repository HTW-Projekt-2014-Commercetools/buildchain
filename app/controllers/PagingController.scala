package controllers

import common.elasticsearch.ElasticsearchClient
import play.api.mvc.{Action, Controller}
import services.PagingService
import scala.concurrent.ExecutionContext.Implicits.global

class PagingController(pagingService: PagingService, elasticsearch: ElasticsearchClient) extends Controller {

  def getProducts(page: Int, size: Int) = Action.async {
    pagingService.getProducts(page, size).map(Ok(_))
  }
}

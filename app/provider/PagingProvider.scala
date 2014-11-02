package provider

import java.util.Locale

import common.sphere.SphereClient
import io.sphere.sdk.queries.PagedQueryResult
import play.api.libs.json._
import io.sphere.sdk.products.Product
import scala.collection.JavaConversions._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class PagingProvider(client: SphereClient) {


  def getProducts(page: Int, size: Int): Future[JsObject] = {
    val productQuery = client.getProductQuery(page, size)
    val products: Future[PagedQueryResult[Product]] = client.execute(productQuery)

    products map(buildJsonBody(page, size) ++ transformResultToJson(_)) recover { case e: Exception =>
      throw e
      Json.obj("products" -> Nil) }
  }

  def transformResultToJson(products: PagedQueryResult[Product]): JsObject = {
    Json.obj("products" -> products.getResults.toList.map(Json.toJson(_)))
  }

  def buildJsonBody(page: Int, size: Int): JsObject = Json.obj("paging" -> Json.obj("page" -> page, "pagesize" -> size))

  implicit val productWrite: Writes[Product] = Writes {
    def getDescription(p: Product) = p.getMasterData.getStaged.getDescription.get().get(Locale.ENGLISH).orElse("NA")
    def getName(p: Product) = p.getMasterData.getStaged.getName.get(Locale.ENGLISH).orElse("NA")

    (p: Product) => JsObject(Seq(
      "id" -> JsString(p.getId),
      "name" -> JsString(getName(p: Product)),
      "description" -> JsString(getDescription(p))
    ))
  }
}

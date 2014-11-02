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


  def getProducts(page: Int, size: Int): Future[JsValue] = {
    val productQuery = client.getProductQuery(page, size)
    val products: Future[PagedQueryResult[Product]] = client.execute(productQuery)

    products map transformResultToJson recover { case e: Exception => Json.obj("products" -> Nil) }
  }

  def transformResultToJson(products: PagedQueryResult[Product]): JsValue = {
    Json.obj("products" -> products.getResults.toList.filter(_.getMasterData.isPublished).map(Json.toJson(_)))
  }

  implicit val productWrite: Writes[Product] = Writes {
    def getPrice(p: Product) = p.getMasterData.getCurrent.get().getMasterVariant.getPrices.get(0).getValue.getNumber.doubleValueExact()
    def getDescription(p: Product) = p.getMasterData.getCurrent.get().getDescription.get().get(Locale.ENGLISH).orElse("NA")
    def getName(p: Product) = p.getMasterData.getCurrent.get().getName.get(Locale.ENGLISH).orElse("NA")

    (p: Product) => JsObject(Seq(
      "id" -> JsString(p.getId),
      "name" -> JsString(getName(p: Product)),
      "description" -> JsString(getDescription(p)),
      "price" -> JsNumber(getPrice(p))
    ))
  }
}

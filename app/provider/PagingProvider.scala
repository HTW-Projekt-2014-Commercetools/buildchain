package provider

import java.util.Locale

import common.sphere.SphereClient
import play.api.libs.json._
import io.sphere.sdk.products.Product
import scala.collection.JavaConversions._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class PagingProvider(client: SphereClient) {
  def getProducts(page: Int, size: Int): Future[JsValue] = {
    val productQuery = client.getProductPage(page, size)

    val filteredProds: Future[JsValue] = client.execute(productQuery).map {
      res => {
        Json.toJson(res.getResults.toList.filter(p => p.getMasterData.isPublished).map(prod => Json.toJson(prod)))
      }
    }.recover {
      case e: Exception =>
        Json.parse{"""{}"""}
    }

    filteredProds
  }

  implicit val productWrite: Writes[Product] = Writes {
    (p: Product) => JsObject(Seq(
      "id" -> JsString(p.getId),
      "name" -> JsString(p.getMasterData.getCurrent.get().getName.get(Locale.ENGLISH).orElse("NA")),
      "description" -> JsString(p.getMasterData.getCurrent.get().getDescription.get().get(Locale.ENGLISH).orElse("NA")),
      "price" -> JsNumber(p.getMasterData.getCurrent.get().getMasterVariant.getPrices.get(0).getValue.getNumber.doubleValueExact())
    ))
  }
}

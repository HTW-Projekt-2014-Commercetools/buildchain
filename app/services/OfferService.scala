package services

import common.domain._
import common.elasticsearch.ElasticsearchClient
import model.{Offer, OfferId, UserId, ProductId}
import org.elasticsearch.index.query.QueryBuilders
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsValue, Json}

import scala.concurrent.Future

class OfferService(elasticsearch: ElasticsearchClient) {
  val offerIndex = IndexName("offers")
  val offerType = TypeName("offers")

  def getOffers: Future[JsValue] = getOffersFromEs.map {
    hits => Json.obj("offers" -> hits.toSeq.map {
      hit => Json.parse(hit.sourceAsString())
    })
  }

  def getOffersFromEs = {
    elasticsearch.search(offerIndex, offerType, QueryBuilders.matchAllQuery()).map(result => result.getHits.getHits)
  }

  def addOffer(offerData: OfferForm): Future[AddOfferResult] = {
    val location = Location(Longitude(offerData.lon), Latitude(offerData.lat))
    val offer = Offer(
      OfferId(offerData.id),
      UserId(offerData.userId),
      ProductId(offerData.productId),
      offerData.tags,
      location,
      Price(offerData.price))

    for {
      es <- writeOfferToEs(offer)
      //TODO write data to sphere IO
    } yield es
  }

  def writeOfferToEs(offer: Offer): Future[AddOfferResult] = {
    elasticsearch.indexDocument(offerIndex, offerType, Json.toJson(offer)).map(response =>
      OfferSaved
    ).recover {
      case _ => OfferCouldNotBeSaved
    }
  }
}

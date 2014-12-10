package services

import common.domain._
import common.elasticsearch.ElasticsearchClient
import model.{Demand, DemandId, UserId}
import org.elasticsearch.index.query.QueryBuilders
import play.api.libs.json.{JsValue, Json}
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class DemandService(elasticsearch: ElasticsearchClient) {
  val demandIndex = IndexName("demands")
  val demandType = TypeName("demands")

  def getDemands: Future[JsValue] = getDemandsFromEs.map {
    hits => Json.obj("demands" -> hits.toSeq.map {
      hit => Json.parse(hit.sourceAsString())
    })
  }

  def getDemandsFromEs = {
    elasticsearch.search(demandIndex, demandType, QueryBuilders.matchAllQuery()).map(result => result.getHits.getHits)
  }

  def addDemand(demandData: DemandForm): Future[AddDemandResult] = {
    val location = Location(Longitude(demandData.lon), Latitude(demandData.lat))
    val demand = Demand(
      DemandId(demandData.id),
      UserId(demandData.userId),
      demandData.tags,
      location,
      Distance(demandData.distance),
      Price(demandData.priceMin),
      Price(demandData.priceMax))

    for {
      es <- writeDemandToEs(demand)
      //TODO write data to sphere IO
    } yield es
  }

  def writeDemandToEs(demand: Demand): Future[AddDemandResult] = {
    elasticsearch.indexDocument(demandIndex, demandType, Json.toJson(demand)).map(response =>
      DemandSaved
    ).recover {
      case _ => DemandCouldNotBeSaved
    }
  }
}

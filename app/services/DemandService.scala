package services

import common.domain._
import common.elasticsearch.{ActionListenerAdapter, ElasticsearchClient}
import model.Demand
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.index.query.QueryBuilders
import play.api.libs.json.{JsValue, JsString, Json}
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import collection.JavaConversions._

sealed trait AddDemandResult
case object DemandCouldNotBeSaved extends AddDemandResult
case object DemandSaved extends AddDemandResult


class DemandService(elasticsearch: ElasticsearchClient) {
  def getDemands: Future[JsValue] = getDemandsFromEs.map(result => Json.toJson(result.toSeq.map(hit => JsString(hit.sourceAsString()))))

  val demandIndex = IndexName("demands")
  val demandType = TypeName("demands")

  def addDemand(demandData: DemandForm): Future[AddDemandResult] = {
    val location = Location(
      Longitude(demandData.lon),
      Latitude(demandData.lat))
    val demand = Demand(
      demandData.userId,
      demandData.tags,
      location,
      demandData.radius,
      demandData.priceMin,
      demandData.priceMax)

    writeDemandToEs(demand).map {
      case 200 => DemandSaved
      case _ => DemandCouldNotBeSaved
    }
    //TODO write data to sphere IO
  }

  def writeDemandToEs(demand: Demand): Future[Int] = {
    elasticsearch.prepareIndex(demandIndex, demandType, Json.toJson(demand)).execute()
    //TODO Error handling here: From zero to hero pls
    Future.successful(200)
  }

  def getDemandsFromEs = {
    elasticsearch.search(demandIndex, demandType, QueryBuilders.matchAllQuery()).map(result => result.getHits.getHits)
  }
}

package model

import common.domain.{Latitude, Longitude, Location}
import play.api.libs.json._

case class Demand(
	uid: String,
	tags: String,
	location: Location,
	distance: Int,
	priceMin: Double,
	priceMax: Double)

object Demand {
	implicit val latitudeReads: Reads[Latitude] = (JsPath \ "lat").read[Double].map(Latitude)
	implicit val latitudeWrites: Writes[Latitude] = Writes { (lat: Latitude) => JsNumber(lat.x) }
	implicit val longitudeReads: Reads[Longitude] = (JsPath \ "lon").read[Double].map(Longitude)
	implicit val longitudeWrites: Writes[Longitude] = Writes { (lon: Longitude) => JsNumber(lon.x) }
	implicit val locationFormat = Json.format[Location]
  implicit val demandFormat = Json.format[Demand]
}
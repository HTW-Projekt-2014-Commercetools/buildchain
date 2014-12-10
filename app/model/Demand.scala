package model

import common.domain.{Latitude, Longitude, Location, Distance, Price}
import play.api.libs.json._

case class DemandId(value: String)
case class Demand(
	id: DemandId,
	uid: UserId,
	tags: String,
	location: Location,
	distance: Distance,
	priceMin: Price,
	priceMax: Price)

object Demand {

	implicit val latitudeReads: Reads[Latitude] = (JsPath \ "lat").read[Double].map(Latitude)
	implicit val latitudeWrites: Writes[Latitude] = Writes { (lat: Latitude) => JsNumber(lat.value) }
	implicit val longitudeReads: Reads[Longitude] = (JsPath \ "lon").read[Double].map(Longitude)
	implicit val longitudeWrites: Writes[Longitude] = Writes { (lon: Longitude) => JsNumber(lon.value) }

	implicit val demandIdFormat = Json.format[DemandId]
	implicit val userIdFormat = Json.format[UserId]
	implicit val locationFormat = Json.format[Location]
	implicit val distanceFormat = Json.format[Distance]
	implicit val priceFormat = Json.format[Price]
	implicit val demandFormat = Json.format[Demand]
}
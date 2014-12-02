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
	implicit val latitudeFormat = Json.format[Latitude]
	implicit val longitudeFormat = Json.format[Longitude]
	implicit val locationFormat = Json.format[Location]
  implicit val demandFormat = Json.format[Demand]
}
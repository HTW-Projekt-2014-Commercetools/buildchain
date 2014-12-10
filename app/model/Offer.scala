package model

import common.domain.{Longitude, Latitude, Location, Price}
import play.api.libs.json._

case class OfferId(value: String)
case class Offer(
  id: OfferId,
  uid: UserId,
  pid: ProductId,
  tags: String,
  location: Location,
  price: Price)

object Offer {

  implicit val offerIdReads: Reads[OfferId] = (JsPath \ "id").read[String].map(OfferId)
  implicit val offerIdWrites: Writes[OfferId] = Writes { (id: OfferId) => JsString(id.value) }
  implicit val userIdReads: Reads[UserId] = (JsPath \ "uid").read[String].map(UserId)
  implicit val userIdWrites: Writes[UserId] = Writes { (uid: UserId) => JsString(uid.value) }
  implicit val productIdReads: Reads[ProductId] = (JsPath \ "pid").read[String].map(ProductId)
  implicit val productIdWrites: Writes[ProductId] = Writes { (pid: ProductId) => JsString(pid.value) }
  implicit val latitudeReads: Reads[Latitude] = (JsPath \ "lat").read[Double].map(Latitude)
  implicit val latitudeWrites: Writes[Latitude] = Writes { (lat: Latitude) => JsNumber(lat.value) }
  implicit val longitudeReads: Reads[Longitude] = (JsPath \ "lon").read[Double].map(Longitude)
  implicit val longitudeWrites: Writes[Longitude] = Writes { (lon: Longitude) => JsNumber(lon.value) }
  implicit val priceReads: Reads[Price] = (JsPath \ "price").read[Double].map(Price)
  implicit val priceWrites: Writes[Price] = Writes { (price: Price) => JsNumber(price.value) }

  implicit val locationFormat = Json.format[Location]
  implicit val offerFormat = Json.format[Offer]
}
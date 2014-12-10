package model

import play.api.libs.json.Json

case class ProductId(value: String)
case class Product(id: ProductId)

object Product {
  implicit val productIdFormat = Json.format[ProductId]
  implicit val productFormat = Json.format[Product]
}
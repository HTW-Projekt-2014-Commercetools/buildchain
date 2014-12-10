package model

import play.api.libs.json.Json

case class UserId(value: String)
case class User(id: UserId)

object User {
  implicit val userIdFormat = Json.format[UserId]
  implicit val userFormat = Json.format[User]
}
package common.domain

case class Location(lon: Longitude, lat: Latitude)
case class Longitude(x: Double) extends AnyVal
case class Latitude(x: Double) extends AnyVal

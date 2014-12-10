package common.domain

case class Location(lon: Longitude, lat: Latitude)
case class Longitude(value: Double) extends AnyVal
case class Latitude(value: Double) extends AnyVal

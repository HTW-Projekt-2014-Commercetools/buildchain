package common.domain

case class DemandForm(userId: String,
                      tags: String,
                      lon: Double,
                      lat: Double,
                      radius: Int,
                      priceMin: Double,
                      priceMax: Double)

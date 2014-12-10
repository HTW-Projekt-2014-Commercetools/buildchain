package common.domain

case class DemandForm(id:String,
                      userId: String,
                      tags: String,
                      lon: Double,
                      lat: Double,
                      distance: Int,
                      priceMin: Double,
                      priceMax: Double)

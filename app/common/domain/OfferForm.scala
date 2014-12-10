package common.domain

case class OfferForm(id:String,
                     userId: String,
                     productId: String,
                     tags: String,
                     lon: Double,
                     lat: Double,
                     price: Double)

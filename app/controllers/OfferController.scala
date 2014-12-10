package controllers

import common.domain.{OfferForm, OfferSaved}
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._
import services.OfferService

import scala.concurrent.Future


class OfferController(offerService: OfferService) extends Controller {

  def form = Action {
    Ok(views.html.offer(offersForm))
  }

  val offersForm: Form[OfferForm] = Form {
    mapping(
      "id" -> nonEmptyText,
      "userId" -> nonEmptyText,
      "productId" -> nonEmptyText,
      "tags" -> nonEmptyText,
      "lon" -> of[Double],
      "lat" -> of[Double],
      "price" -> of[Double]
    )(OfferForm.apply)(OfferForm.unapply)
  }

  def addOffer = Action.async {
  implicit request =>
    offersForm.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        Future.successful(BadRequest(views.html.offer(formWithErrors)))
      },
      offerData => {
        /* binding success, you get the actual value. */
        offerService.addOffer(offerData).map {
        case OfferSaved => Redirect(routes.OfferController.getOffers)
        case _ => Redirect(routes.OfferController.addOffer)
        }

      }
    )
  }

  def getOffers = Action.async {
    offerService.getOffers.map(json => Ok(json))
  }
}
package controllers

import common.domain.{DemandSaved, DemandForm}
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import services.DemandService
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future


class DemandController(demandService: DemandService) extends Controller {

  def form = Action {
    Ok(views.html.demand(demandsForm))
  }

  val demandsForm: Form[DemandForm] = Form {
    mapping(
      "userId" -> nonEmptyText,
      "tags" -> nonEmptyText,
      "lon" -> of[Double],
      "lat" -> of[Double],
      "radius" -> number(min = 0),
      "priceMin" -> of[Double],
      "priceMax" -> of[Double]
    )(DemandForm.apply)(DemandForm.unapply)
  }

  def addDemand = Action.async {
  implicit request =>
    demandsForm.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        Future.successful(BadRequest(views.html.demand(formWithErrors)))
      },
      demandData => {
        /* binding success, you get the actual value. */
        demandService.addDemand(demandData).map {
        case DemandSaved => Redirect(routes.DemandController.getDemands)
        case _ => Redirect(routes.DemandController.addDemand)
        }

      }
    )
  }

  def getDemands = Action.async {
    demandService.getDemands.map(json => Ok(json))
  }
}
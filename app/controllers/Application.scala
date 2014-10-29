package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Play.current.configuration.getString("test.test").map(println(_))
    Ok(views.html.index("Your new application is ready."))
  }

  def foo = Action {
    Ok("bar")
  }

}
package common.sphere

import play.api.{Mode, Play}

object SphereClientFactory {
  lazy val instance = getInstance

  def apply(): SphereClient = {
    instance
  }

  def getInstance = Play.current.mode match {
    case Mode.Dev => DevSphereClient
    case Mode.Prod => ProdSphereClient
    case Mode.Test => MockSphereClient
  }
}

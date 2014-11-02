package common.sphere

import play.api.{Mode, Play}

object SphereClientFactory {
  lazy val instance = getInstance

  def apply(): SphereClient = {
    instance
  }

  def getInstance = Play.current.mode match {
    case Mode.Dev => RemoteSphereClient
    case Mode.Prod => RemoteSphereClient
    case Mode.Test => ProjectMockSphereClient
  }
}

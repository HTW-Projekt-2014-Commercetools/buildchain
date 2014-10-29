package common

import common.sphere.SphereClientFactory
import controllers.PagingController
import provider.PagingProvider

trait WireDependencies {
  import com.softwaremill.macwire.MacwireMacros._

  lazy val sphereClient   = SphereClientFactory()
  lazy val pagingProvider = wire[PagingProvider]
  lazy val pagingController = wire[PagingController]
}

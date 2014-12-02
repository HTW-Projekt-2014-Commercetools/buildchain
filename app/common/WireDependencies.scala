package common

import common.elasticsearch.ElasticsearchClientFactory
import common.sphere.SphereClientFactory
import controllers.PagingController
import services.PagingService

trait WireDependencies {
  import com.softwaremill.macwire.MacwireMacros._

  lazy val sphereClient   = SphereClientFactory()
  lazy val elasticsearchClient = ElasticsearchClientFactory()
  lazy val pagingProvider = wire[PagingService]
  lazy val pagingController = wire[PagingController]
}

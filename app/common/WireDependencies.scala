package common

import common.elasticsearch.ElasticsearchClientFactory
import common.sphere.SphereClientFactory
import controllers.{DemandController, PagingController}
import services.{DemandService, PagingService}

trait WireDependencies {
  import com.softwaremill.macwire.MacwireMacros._

  //Clients
  lazy val sphereClient   = SphereClientFactory()
  lazy val elasticsearchClient = ElasticsearchClientFactory()

  // Services
  lazy val pagingService = wire[PagingService]
  lazy val demandService = wire[DemandService]

  // Controllers
  lazy val pagingController = wire[PagingController]
  lazy val demandController = wire[DemandController]
}

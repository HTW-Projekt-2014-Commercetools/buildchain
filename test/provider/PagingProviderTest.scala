package provider

import common.sphere.SphereClientFactory
import org.specs2.mutable.Specification
import play.api.libs.json.Json
import play.api.test.WithApplication

class PagingProviderTest extends Specification {

  "Paging provider" should {
    "buildJsonBody must construct correct paging information" in new WithApplication {
      val provider = new PagingProvider(SphereClientFactory.getInstance)
      provider.buildJsonBody(3, 5) must beEqualTo(Json.obj("paging" -> Json.obj("page" -> 3, "pagesize" -> 5)))
    }
  }

}

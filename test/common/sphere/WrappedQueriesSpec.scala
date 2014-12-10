package common.sphere

import org.specs2.mutable.Specification
import play.api.test.WithApplication

class WrappedQueriesSpec extends Specification {

  "WrappedQueries" should {
    "getProductQuery must return valid Product Query DSL Object with offset and limit" in new WithApplication {
      val productQuery = SphereClientFactory.getInstance.getProductQuery(3, 5)
      productQuery.limit().get() mustEqual 5
      productQuery.offset().get() mustEqual 15
    }
  }

}

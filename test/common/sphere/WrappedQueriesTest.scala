package common.sphere

import org.specs2.mutable.Specification
import play.api.test.WithApplication

class WrappedQueriesTest extends Specification {

  "WrappedQueries" should {
    "getProductPage must return valid Product Query DSL Object with offset and limit" in new WithApplication {
      val productQuery = RemoteSphereClient.getProductQuery(3, 5)
      productQuery.limit().get() mustEqual 5
      productQuery.offset().get() mustEqual 15
    }
  }

}

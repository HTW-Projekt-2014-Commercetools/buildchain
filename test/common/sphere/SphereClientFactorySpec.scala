package common.sphere

import org.specs2.mutable.Specification
import play.api.test.WithApplication

class SphereClientFactorySpec extends Specification {
  "SphereClientFactory" should {
    "getInstance must return MockClient for tests" in new WithApplication {
      SphereClientFactory.getInstance must be(RemoteSphereClient)
    }
  }
}

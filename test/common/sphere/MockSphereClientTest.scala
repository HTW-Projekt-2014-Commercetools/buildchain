package common.sphere

import common.sphere.MockSphereClient.MockClient
import org.specs2.mutable.Specification
import play.api.test.WithApplication

class MockSphereClientTest extends Specification {

  "MockSphereClient" should {

    "createSphereClient return an MockClient object in createMockClient" in new WithApplication {
      MockSphereClient.createSphereClient() must haveClass[MockClient]
    }
  }
}

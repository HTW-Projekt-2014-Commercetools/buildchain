import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beNone
    }

    "get more products must return test Json" in new WithApplication{
      val products = route(FakeRequest(GET, "/products/page/3/size/2")).get

      status(products) must equalTo(OK)
      contentType(products) must beSome.which(_ == "application/json")
      contentAsString(products) must contain ("{\"paging\":{\"page\":3,\"pagesize\":2},\"products\":[{\"id\":\"foo-id\",\"name\":\"name\",\"description\":\"description\"}]}")
    }
  }
}

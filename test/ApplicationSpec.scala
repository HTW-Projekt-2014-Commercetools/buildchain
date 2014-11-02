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

    "render the index page" in new WithApplication{
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Blub")
    }

    "get more products must return Json" in new WithApplication{
      val products = route(FakeRequest(GET, "/products?offset=3&size=2")).get

      status(products) must equalTo(OK)
      contentType(products) must beSome.which(_ == "application/json")
      contentAsString(products) must contain ("{\"products\":[{\"id\":\"foo-id\",\"name\":\"name\",\"description\":\"description\"}]}")
    }
  }
}

package controllers

import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._
import com.yansoft.controllers.HomeController
import org.scalatest.mockito.MockitoSugar

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class HomeControllerSpec extends PlaySpec with MockitoSugar {

  "HomeController GET" should {

    "render the index page from a new instance of controller" in {
      val controller = new HomeController
      val home = controller.index().apply(FakeRequest())

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
    }

    
  }
}

package controllers

import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._
import com.yansoft.controllers.HomeController
import org.scalatest.mock.MockitoSugar


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

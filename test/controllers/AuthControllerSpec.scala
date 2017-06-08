package controllers

import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._
import play.api.mvc._
import com.yansoft.controllers.AuthController
import org.scalatest.mock.MockitoSugar
import com.yansoft.services.security.SecurityService
import be.objectify.deadbolt.scala.DeadboltActions
import scala.concurrent.{ Future, Await }
import play.api.libs.json._
import org.scalatestplus.play._
import org.mockito.Mockito._
import com.yansoft.services.security.SecurityService._
import com.yansoft.controllers.AuthController.CreateAccount

class AuthControllerSpec extends PlaySpec with MockitoSugar {

  import com.yansoft.serializers.json.AuthObjectSerializers._

  val service = mock[SecurityService]
  val deadboltActions = mock[DeadboltActions]
  val controller = new AuthController(service, deadboltActions)

  "AuthController#create success " should {
    val acctToCreate = CreateAccount("yfayman@gmail.com", "yan", "password")
    val json = Json.toJson(acctToCreate)
    val request: Request[JsValue] = FakeRequest("POST", "/").withBody(json)
    
    val serviceRequest = CommonCreateAccountRequest(acctToCreate.username, acctToCreate.email, acctToCreate.password)
    val serviceResponse = CommonCreateAccountResponse(true, Option(CommonAccount(5, acctToCreate.username, acctToCreate.email, None, None)), None)
    when(service.createAccount(serviceRequest)).thenReturn(Future.successful(serviceResponse))
    
    val result = controller.create.apply(request)
    assert(status(result) == play.api.http.Status.OK)
  }
}
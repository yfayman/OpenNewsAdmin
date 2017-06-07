package services

import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import com.yansoft.services.security.SecurityServiceImpl
import com.yansoft.services.security.SecurityServiceImpl
import com.yansoft.services.security.BCryptPasswordHash
import com.yansoft.services.security.UUIDTokenGenerator
import com.yansoft.daos.security.SecurityDao
import scala.language.postfixOps
import scala.concurrent.{ Future, Await }
import scala.concurrent.duration._
import org.mockito.Mockito._
import scala.util.{ Try, Success, Failure }
import org.mockito.ArgumentMatchers

class SecurityServiceTest extends FunSuite with MockitoSugar {

  import com.yansoft.services.security.SecurityService._
  import com.yansoft.daos.security.SecurityDao._

  val dao = mock[SecurityDao]
  val service = new SecurityServiceImpl(dao, new BCryptPasswordHash, new UUIDTokenGenerator)

  test("Create Account Test - Success") {
    val request = CommonCreateAccountRequest("yan", "yfayman@gmail.com", "password")
    val daoCreateOutcome = Success(UserData(5, request.username, request.email, request.password, None, None))
    when(dao.create(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Future.successful(daoCreateOutcome))

    val responseFuture = service.createAccount(request)
    val response = Await.result(responseFuture, 2.seconds)
    assert(response.success)
    assert(response.errors.isEmpty)
    assert(response.account.isDefined)
  }

  test("Create Account Test - Fail") {
    val request = CommonCreateAccountRequest("yan", "yfayman@gmail.com", "password")
    val daoCreateOutcome = Failure(new Exception("username is taken"))
    when(dao.create(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Future.successful(daoCreateOutcome))
   
    val responseFuture = service.createAccount(request)
    val response = Await.result(responseFuture, 2.seconds)
    
    assert(!response.success)
    assert(response.errors.isDefined)
    assert(response.account.isEmpty)
  }

  test("Login Test - Success") {

  }

  test("Login Test - Fail") {

  }

  test("Get Account Info - Success") {

  }

  test("Get Account Info - Fail") {

  }

  test("Renew auth - Success") {

  }

  test("Renew auth - Fail") {

  }

}
package services

import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar
import com.yansoft.services.security.SecurityServiceImpl
import com.yansoft.services.security.SecurityServiceImpl
import com.yansoft.services.security.BCryptPasswordHash
import com.yansoft.services.security.UUIDTokenGenerator

class SecurityServiceTest extends FunSuite with MockitoSugar {

  import com.yansoft.services.security.SecurityService._

  val service = new SecurityServiceImpl(new BCryptPasswordHash, new UUIDTokenGenerator)

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
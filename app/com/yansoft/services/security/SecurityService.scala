package com.yansoft.services.security
import be.objectify.deadbolt.scala.models.Subject
import be.objectify.deadbolt.scala.models._
import scala.concurrent.Future
import com.yansoft.utilities.ApplicationLogging

object SecurityService {

  case class CommonLoginRequest(email:String, password:String)
  case class CommonLoginResponse(success: Boolean, account: Option[CommonAccount])
  
  case class CommonCreateAccountRequest(username:String, email:String, password:String)
  case class CommonCreateAccountResponse(success:Boolean, account:Option[CommonAccount], errors:Option[List[String]]) // replace with enum later
  
  case class CommonGetAccountInfoResponse(account: Option[CommonAccount], activeAuth: Boolean, message: String)
  case class CommonRenewAuthAccountRequest(ca: CommonAccount)
  case class CommonAccount(userId: Int, username: String, email: String, token: Option[String], tokenExp: Option[Long]) extends Subject {
    def identifier: String = userId.toString()

    def roles: List[Role] = List()

    def permissions: List[Permission] = List()
  }

}

trait SecurityService extends ApplicationLogging {

  import SecurityService._

  def login(request:CommonLoginRequest): Future[CommonLoginResponse]

  def getAccountInfoByToken(token: String): Future[CommonGetAccountInfoResponse]

  def renewAuth(request: CommonRenewAuthAccountRequest): Future[Boolean]
  
  def createAccount(request:CommonCreateAccountRequest):Future[CommonCreateAccountResponse]

}

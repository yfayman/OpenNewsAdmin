package com.yansoft.services.security

import scala.concurrent.Future
import com.yansoft.daos.security.SecurityDaoSlickImpl
import com.yansoft.daos.security.SecurityDao
import scala.util.Success
import scala.util.Failure
import com.yansoft.utilities.GlobalExecutionContextAware

class SecurityServiceImpl(dao: SecurityDao, hasher: PasswordHasher, tokenGenerator: TokenGenerator)
    extends SecurityService with GlobalExecutionContextAware {
  import SecurityService._

  def login(request: CommonLoginRequest): Future[CommonLoginResponse] = ???

  def getAccountInfoByToken(token: String): Future[CommonGetAccountInfoResponse] = ???

  def renewAuth(req: CommonRenewAuthAccountRequest): Future[Boolean] = ???

  def createAccount(request: CommonCreateAccountRequest): Future[CommonCreateAccountResponse] = {
    
    val hashedPassword = hasher.hash(request.password)
    
    dao.create(request.username, hashedPassword, request.email).map {
      _ match {
        case Success(userData) => {
          val time = userData.authTokenExp.map { timestamp => timestamp.getTime }
          val commonAccount = CommonAccount(userData.id, userData.username, userData.email, userData.authToken, time)
          CommonCreateAccountResponse(true, Option(commonAccount), None)
        }
        case Failure(e) => {
          CommonCreateAccountResponse(false, None, Option(List(e.getMessage)))
        }
      }
    }
  }
  
}
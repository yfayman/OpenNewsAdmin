package com.yansoft.services.security

import scala.concurrent.Future

class SecurityServiceImpl(hasher: PasswordHasher, tokenGenerator: TokenGenerator) extends SecurityService{
  import SecurityService._

  def login(email: String, password: String): Future[CommonLoginResponse] = ???

  def getAccountInfoByToken(token: String): Future[CommonGetAccountInfoResponse] = ???

  def renewAuth(req: CommonRenewAuthAccountRequest): Future[Boolean] = ???
}
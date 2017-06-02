package com.yansoft.deadbolt

import be.objectify.deadbolt.scala.models.Subject
import be.objectify.deadbolt.scala.{ AuthenticatedRequest, DynamicResourceHandler, DeadboltHandler }
import play.api.mvc.{ Results, Result, Request }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import org.slf4j.LoggerFactory
import com.yansoft.services.security._
import com.yansoft.services.security.SecurityService._
import scala.util.{ Try, Success, Failure }
import com.yansoft.utilities.ApplicationLogging

/**
 * This handler just cares if a user exists
 */
class UsesExistsDeadboltHandler(securityService: SecurityService) extends DeadboltHandler with ApplicationLogging {

  override def beforeAuthCheck[A](request: Request[A]): Future[Option[Result]] = {
    logger.info("Before auth check on UsesExistsDeadboltHandler")
    Future { None }
  }

  override def getDynamicResourceHandler[A](request: Request[A]): Future[Option[DynamicResourceHandler]] = {
    logger.info("getDynamicResourceHandler on MyDeadboltHandler")
    Future { None }
  }

  override def getSubject[A](request: AuthenticatedRequest[A]): Future[Option[Subject]] = {
    logger.info("Getting subject")
    val tokenOpt = request.headers.get("authToken")
    if (tokenOpt.isDefined) {
      securityService.getAccountInfoByToken(tokenOpt.get).map { gair => gair.account }
        .andThen { case Success(r) => if (r.isDefined && shouldRenew(r.get)) securityService.renewAuth(CommonRenewAuthAccountRequest(r.get)) }
    } else {
      Future.successful(None)
    }
  }
  override def onAuthFailure[A](request: AuthenticatedRequest[A]): Future[Result] = {
    logger.info("Auth failure")
    Future.successful(Results.Forbidden)
  }

  // if the token expires in 20 minutes, renew it to 30 minutes
  private def shouldRenew(ca: CommonAccount): Boolean =
    ca.tokenExp.isDefined && ca.tokenExp.get - System.currentTimeMillis() > (20 * 60 * 1000)

}
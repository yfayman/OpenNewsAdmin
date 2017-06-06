package com.yansoft.daos.security

import scala.concurrent.Future
import scala.util.Try
import play.api.inject.ApplicationLifecycle
import slick.driver.PostgresDriver
import slick.driver.PostgresDriver.api._
import slick.jdbc.JdbcBackend._
import scala.language.postfixOps
import com.yansoft.slick.Tables
import scala.concurrent.duration._
import scala.concurrent.Await
import java.sql.Timestamp
import com.yansoft.utilities.GlobalExecutionContextAware

class SecurityDaoSlickImpl(db: slick.jdbc.JdbcBackend.Database, lifecycle: ApplicationLifecycle)
    extends SecurityDao with GlobalExecutionContextAware {

  import com.yansoft.daos.security.SecurityDao._

  val users = TableQuery[Tables.User]
  val userTypes = TableQuery[Tables.UserType]

  lazy val userUserTypeId: Int = {
    val futureUserId = getFutureUserTypeIdByRefCode("USER")
    Await.result(futureUserId, 3 seconds)
  }

  lazy val userAdminTypeId: Int = {
    val futureUserId = getFutureUserTypeIdByRefCode("ADMIN")
    Await.result(futureUserId, 3 seconds)
  }

  def create(username: String, password: String, email: String): Future[Try[UserData]] = {
    val now = new Timestamp(System.currentTimeMillis())
    val userId = users.returning(users.map { _.userId }) += Tables.UserRow(-1, username, email, password, userAdminTypeId, true, None, None, now, now)
    val futureTryResult = db.run(userId.asTry)
    futureTryResult.map { _.map { newUserId => UserData(newUserId, email, username, password, None, None) } }
  }

  def checkEmail(email: String): Future[Option[Int]] = ???

  def saveAuthToken(id: Int, authToken: String, newExpiration: Long): Future[Boolean] = ???

  def getAccountByAuthToken(token: String): Future[Option[UserData]] = ???

  def getAccountById(id: Int): Future[Option[UserData]] = ???

  def renewAuth(userId: Int, newExpiration: Long): Future[Boolean] = ???

  private def convertTableUserToUser(utr: Tables.UserRow): UserData = {
    UserData(utr.userId, utr.email, utr.username, utr.password, utr.authToken, utr.authExpiration)
  }

  private def getFutureUserTypeIdByRefCode(refCode: String): Future[Int] = {
    val idQuery = userTypes.filter { ut => ut.refCode === refCode }.map { ut => ut.userTypeId }
    db.run(idQuery.result.head)
  }

  lifecycle.addStopHook { () => Future.successful(db.close()) }

}
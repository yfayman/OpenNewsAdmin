package com.yansoft.daos.article

import scala.concurrent.Future
import java.sql.Timestamp

object UserDao {
  
  case class UserData(id: Int, email: String, username: String, password: String, authToken: Option[String], authTokenExp: Option[Timestamp])

}

trait UserDao {

  import UserDao._

  def getPasswordByEmail(email: String): Future[Option[String]]

  def create(username: String, password: String, email: String): Future[(Boolean, String)];

  def checkEmail(email: String): Future[Option[Int]]

  def getAccountByEmail(email: String): Future[Option[UserData]]

  def saveAuthToken(id: Int, authToken: String, newExpiration: Long): Future[Boolean]

  def getAccountByAuthToken(token: String): Future[Option[UserData]]

  def getAccountById(id: Int): Future[Option[UserData]]

  def renewAuth(userId: Int, newExpiration: Long): Future[Boolean]

}
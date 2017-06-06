package com.yansoft.controllers

import play.mvc.Controller
import com.yansoft.utilities.ApplicationLogging
import com.yansoft.services.security.SecurityService
import be.objectify.deadbolt.scala.DeadboltActions
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import play.api.mvc.Results._

object AuthController {
  case class Login(email: String, password: String)
  case class CreateAccount(email: String, username: String, password: String)
}

class AuthController(service: SecurityService, deadbolt: DeadboltActions)
    extends Controller with ApplicationLogging {

  import AuthController._
  
  def login = deadbolt.SubjectNotPresent()(BodyParsers.parse.json) { request =>
    ???
  }
  
  def create = deadbolt.SubjectNotPresent()(BodyParsers.parse.json) { request =>
    ???
  }

}
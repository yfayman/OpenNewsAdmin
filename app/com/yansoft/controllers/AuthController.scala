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
import com.yansoft.services.security.SecurityService.CommonLoginResponse
import com.yansoft.utilities.GlobalExecutionContextAware

object AuthController {
  case class Login(email: String, password: String)
  case class CreateAccount(email: String, username: String, password: String)
}

class AuthController(service: SecurityService, deadbolt: DeadboltActions)
    extends Controller with ApplicationLogging with GlobalExecutionContextAware {

  import AuthController._
  import com.yansoft.serializers.json.AuthObjectSerializers._
  import com.yansoft.services.security.SecurityService._

  def login = deadbolt.SubjectNotPresent()(BodyParsers.parse.json) { request =>
    val login = request.body.validate[Login]
    login.fold(
      errors => {
        Future.successful(BadRequest(JsError.toJson(errors)))
      },
      login => {
        val serviceLoginReq = CommonLoginRequest(login.email, login.password)
        service.login(serviceLoginReq).map { loginResponse =>
          loginResponse match {
            case CommonLoginResponse(true, accOpt) => Ok(Json.toJson(loginResponse))
            case _                                 => Unauthorized
          }
        }
      })
  }

  def create = Action.async(BodyParsers.parse.json) { request =>
    val createAccount = request.body.validate[CreateAccount]
    
    createAccount.fold(
      errors => {
        Future.successful(BadRequest(JsError.toJson(errors)))
      },
      createAccount => {
        val serviceCreateAccountReq = CommonCreateAccountRequest(createAccount.username, createAccount.email, createAccount.password)
        service.createAccount(serviceCreateAccountReq).map {res => res match{
          case CommonCreateAccountResponse(true, accOpt, None) => Ok(Json.toJson(res))
          case CommonCreateAccountResponse(false, accOpt, Some(errors)) => BadRequest(Json.toJson(errors))
          case _ => BadRequest
        } }
      })    
  }

}
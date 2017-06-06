package com.yansoft.serializers.json

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import com.yansoft.controllers._
import com.yansoft.services.security.SecurityService._
import com.yansoft.controllers.AuthController._

object AuthObjectSerializers {
  implicit val accountWrites = new Writes[CommonAccount] {
    def writes(acc: CommonAccount): JsValue = Json.obj("userId" -> acc.userId, "username" -> acc.username, "email" -> acc.email, "token" -> acc.token)
  }

  implicit val accountReads = new Reads[CommonAccount] {
    def reads(json: JsValue): JsResult[CommonAccount] = {
      val userIdResult = (json \ "userId").validate[Int]
      val usernameResult = (json \ "username").validate[String]
      val emailResult = (json \ "email").validate[String]
      val tokenResult = (json \ "email").validate[String]
      val expOpt = (json \ "tokenExp").validate[Long].asOpt

      for {
        userId <- userIdResult
        username <- usernameResult
        email <- emailResult
        token <- tokenResult
      } yield (CommonAccount(userId, username, email, Option(token), expOpt))
    }
  }

  implicit val loginResponseWrites = new Writes[CommonLoginResponse] {
    def writes(lr: CommonLoginResponse): JsValue = Json.obj("accountInfo" -> Json.toJson(lr.account.getOrElse(null)))
  }

  implicit val loginResponseReads = new Reads[CommonLoginResponse] {
    def reads(json: JsValue): JsResult[CommonLoginResponse] = {
      val successResult = (json \ "success").validate[Boolean]
      val accOptResult = (json \ "account").validateOpt[CommonAccount]

      for {
        success <- successResult
        accOpt <- accOptResult
      } yield (CommonLoginResponse(success, accOpt))
    }
  }

  implicit val commonCreateAccountResponseWrites = new Writes[CommonCreateAccountResponse] {
    def writes(ccar: CommonCreateAccountResponse): JsValue =
      Json.obj("success" -> ccar.success, "account" -> ccar.account, "errors" -> ccar.errors)
  }

  implicit val commonCreateAccountResponseReads = new Reads[CommonCreateAccountResponse] {
    def reads(json: JsValue): JsResult[CommonCreateAccountResponse] = {
      val successResult = (json \ "success").validate[Boolean]
      val accOptResult = (json \ "account").validateOpt[CommonAccount]
      val errorsOptResult = (json \ "errors").validateOpt[List[String]]

      for {
        success <- successResult
        accOpt <- accOptResult
        errorsOpt <- errorsOptResult
      } yield (CommonCreateAccountResponse(success, accOpt, errorsOpt))
    }
  }

  implicit val getAccountInfoResponseReads = new Reads[CommonGetAccountInfoResponse] {
    def reads(json: JsValue): JsResult[CommonGetAccountInfoResponse] = {
      val accOptResult = (json \ "account").validateOpt[CommonAccount]
      val activeAuthResult = (json \ "activeAuth").validate[Boolean]
      val messageResult = (json \ "message").validate[String]

      for {
        accOpt <- accOptResult
        activeAuth <- activeAuthResult
        message <- messageResult
      } yield (CommonGetAccountInfoResponse(accOpt, activeAuth, message))
    }
  }

  implicit val getAccountInfoResponseWrites = new Writes[CommonGetAccountInfoResponse] {
    def writes(gai: CommonGetAccountInfoResponse): JsValue =
      Json.obj("account" -> gai.account, "activeAuth" -> gai.activeAuth, "message" -> gai.message)
  }

  implicit val loginReads: Reads[Login] = (
    (JsPath \ "email").read[String](email) and
    (JsPath \ "password").read[String])(Login.apply _)

  implicit val createAccountWrites = new Writes[CreateAccount] {
    def writes(ca: CreateAccount): JsValue =
      Json.obj("email" -> ca.email, "username" -> ca.username, "password" -> ca.password)
  }

  implicit val createAccountReads: Reads[CreateAccount] = (
    (JsPath \ "email").read[String](email) and
    (JsPath \ "username").read[String] and
    (JsPath \ "password").read[String])(CreateAccount.apply _)
}
package com.yansoft.daos.security

import scala.concurrent.Future
import java.sql.Timestamp
import scala.util.Try

object SecurityDao{
  case class UserData(id:Int, email:String, username:String, password:String, authToken:Option[String], authTokenExp:Option[Timestamp] )
}

trait SecurityDao {
  
    import SecurityDao._
        
    def create(username:String, password:String, email:String) :Future[Try[UserData]]
    
    def checkEmail(email:String):Future[Option[Int]]
    
    def saveAuthToken(id:Int, authToken:String, newExpiration:Long):Future[Boolean]
    
    def getAccountByAuthToken(token:String):Future[Option[UserData]]
    
    def getAccountById(id:Int):Future[Option[UserData]]
    
    def renewAuth(userId:Int, newExpiration:Long):Future[Boolean]
    
}



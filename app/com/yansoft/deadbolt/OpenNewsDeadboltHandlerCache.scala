package com.acadaca.fakenews.deadbolt

import be.objectify.deadbolt.scala.cache.HandlerCache
import be.objectify.deadbolt.scala._
import com.yansoft.services.security.SecurityService
import com.yansoft.deadbolt.UsesExistsDeadboltHandler


class OpenNewsDeadboltHandlerCache (securityService: SecurityService) extends HandlerCache {

  private val logger = org.slf4j.LoggerFactory.getLogger(this.getClass)

  val defaultHandler: DeadboltHandler = new UsesExistsDeadboltHandler(securityService)
  
  val handlers: Map[Any, DeadboltHandler] = 
    Map(HandlerKeys.defaultKey -> defaultHandler )

  // Get the default handler.
  override def apply(): DeadboltHandler = {
    defaultHandler
  }

  // Get a named handler
  override def apply(handlerKey: HandlerKey): DeadboltHandler = {
    handlers.getOrElse(handlerKey, defaultHandler)
  }
}
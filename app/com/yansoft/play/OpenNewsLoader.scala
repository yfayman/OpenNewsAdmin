package com.yansoft.play

import play.api._
import play.api.i18n._
import play.api.inject._
import play.api.routing.Router
import router.Routes
import scala.concurrent.ExecutionContext
import play.filters.gzip.GzipFilter
import com.yansoft.controllers.HomeController
import be.objectify.deadbolt.scala.DeadboltComponents
import be.objectify.deadbolt.scala.cache.{ DefaultPatternCache, CompositeCache, HandlerCache, PatternCache }
import play.api.cache.EhCacheComponents
import com.yansoft.deadbolt.PassThroughCompositeCache
import com.acadaca.fakenews.deadbolt.OpenNewsDeadboltHandlerCache
import com.yansoft.services.security.SecurityServiceImpl
import com.acadaca.fakenews.deadbolt.OpenNewsDeadboltHandlerCache
import com.acadaca.fakenews.deadbolt.OpenNewsDeadboltHandlerCache
import com.yansoft.deadbolt.OpenNewsDeadboltEc

class OpenNewsLoader extends ApplicationLoader {
  def load(context: ApplicationLoader.Context) = {
    LoggerConfigurator(context.environment.classLoader).foreach {
      _.configure(context.environment)
    }
    new MyComponents(context).application
  }
}

class MyComponents(context: ApplicationLoader.Context)
    extends BuiltInComponentsFromContext(context)
    with I18nComponents
    with DeadboltComponents
    with EhCacheComponents {

  // Services
  lazy val securityService = new SecurityServiceImpl

  //Deadbolt
  override lazy val defaultEcContextProvider = new OpenNewsDeadboltEc
  override lazy val patternCache: PatternCache = new DefaultPatternCache(defaultCacheApi)
  def compositeCache: CompositeCache = new PassThroughCompositeCache
  def handlers: HandlerCache = new OpenNewsDeadboltHandlerCache(securityService)

  implicit val ec = ExecutionContext.global

  lazy val gzipFilter = new GzipFilter

  override lazy val httpFilters = Seq(gzipFilter)

  lazy val homeController = new HomeController

  lazy val assets = new controllers.Assets(httpErrorHandler)

  lazy val router: Router = new Routes(httpErrorHandler, homeController, assets)
}
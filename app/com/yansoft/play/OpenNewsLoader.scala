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
import com.acadaca.fakenews.deadbolt.OpenNewsDeadboltHandlerCache
import com.acadaca.fakenews.deadbolt.OpenNewsDeadboltHandlerCache
import com.yansoft.deadbolt.OpenNewsDeadboltEc
import com.yansoft.services.article.ArticleServiceImpl
import com.yansoft.services.security._
import com.yansoft.services.jobs.JobServiceImpl
import com.yansoft.scrapper.JsoupScrapper
import akka.actor.{ ActorSystem }
import com.yansoft.jobs.UpdateArticlesJob
import com.yansoft.services.ArticleFinder.ArticleFinderServiceImpl
import com.yansoft.utilities.ApplicationLogging

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
    with EhCacheComponents{
  
  //Akka
  lazy val system = ActorSystem("jobs")

  //Scrapper
  lazy val scrapper = new JsoupScrapper

  //Jobs
  lazy val updateArticlesJob = new UpdateArticlesJob(system, articleFinderService.getActiveArticleFinders)(articleService, scrapper)

  // Services
  lazy val securityService = new SecurityServiceImpl(new BCryptPasswordHash, new UUIDTokenGenerator)
  lazy val articleService = new ArticleServiceImpl
  lazy val articleFinderService = new ArticleFinderServiceImpl
  lazy val jobService = new JobServiceImpl(configuration, List(updateArticlesJob), system)

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
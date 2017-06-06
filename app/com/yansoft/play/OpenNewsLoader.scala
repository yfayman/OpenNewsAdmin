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
import play.api.db.evolutions.EvolutionsComponents
import play.api.db.slick.evolutions.SlickEvolutionsComponents
import play.api.db.slick.SlickComponents
import com.yansoft.controllers.AuthController
import com.yansoft.daos.security.SecurityDaoSlickImpl
import slick.jdbc.JdbcBackend._
import scala.concurrent.Future

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
    with EhCacheComponents
    with EvolutionsComponents
    with SlickEvolutionsComponents
    with SlickComponents {

  //Akka
  lazy val system = ActorSystem("jobs")

  //Scrapper
  lazy val scrapper = new JsoupScrapper

  //Jobs
  lazy val updateArticlesJob = new UpdateArticlesJob(system, articleFinderService.getActiveArticleFinders)(articleService, scrapper)

  //DB
  lazy val db = Database.forConfig("main")
  
  // DAOs
  lazy val securityDao = new SecurityDaoSlickImpl(db)

  // Services
  lazy val securityService = new SecurityServiceImpl(securityDao, new BCryptPasswordHash, new UUIDTokenGenerator)
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
  lazy val authController = new AuthController(securityService, deadboltActions)

  lazy val assets = new controllers.Assets(httpErrorHandler)

  lazy val router: Router = new Routes(httpErrorHandler, homeController, authController, assets)
  
  
  // Close the DB when app stops
  applicationLifecycle.addStopHook { () => Future.successful(db.close()) }

}
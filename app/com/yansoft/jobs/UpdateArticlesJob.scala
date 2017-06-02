package com.yansoft.jobs

import akka.actor._
import com.yansoft.rss.ArticleFinder
import com.yansoft.services.article.ArticleService
import com.yansoft.scrapper.ArticleScrapper

class UpdateArticlesJob(system: ActorSystem, articleFinders: List[ArticleFinder])(implicit val articleService: ArticleService, scrapper: ArticleScrapper)
    extends Job {

  def start(): Unit = ???

  def stop(): Unit = ???
  
  def name = "UpdateArticlesJob"
}
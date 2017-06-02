package com.yansoft.scrapper

import scala.concurrent.Future

object ArticleScrapper{
  
  case class ScrappedData(url:String, title:String, shortDescription:String, html:String)
}

trait ArticleScrapper {
  
  import ArticleScrapper._
  
  def getArticle(url:String):Future[Option[ScrappedData]]
  
}


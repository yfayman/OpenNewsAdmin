package com.yansoft.services.article

import scala.concurrent.Future
import scala.concurrent.ExecutionContext
import com.acadaca.fakenews.utilities._
import scala.util.{ Try, Success, Failure }

object ArticleService {
 
  case class CommonArticle(id: Int, url: String, title: String, html: String, shortDescription: String, userId: Option[Int], status: ArticleStatusEnum.Value)
  case class CommonCreateArticleRequest(url: String, title: String, html: String, shortDescription: String, userId: Option[Int])
  case class CommonCreateArticleResponse(id: Option[Int], success: Boolean, error: Option[ArticleServiceError.Value])
  case class CommonGetArticlesRequest(skipHtml: Boolean)
  case class CommonArticleUpdateStatusRequest(articleId: Int, newStatus: ArticleStatusEnum.Value)
  case class CommonArticleUpdateStatusResponse(success: Boolean)
  case class CommonArticleDataRequest(articleCount: Int, orderBy: String)

}

 object ArticleServiceError extends Enumeration {
    type ArticleServiceError = Value
    val DATABASE_ERROR, BAD_DATA = Value
  }

  object ArticleStatusEnum extends Enumeration {
    type ArticleStatusEnum = Value
    val ACTIVE, PENDING, DELETED = Value
  }


trait ArticleService {

  import ArticleService._ 
  
  def getArticleById(id: Int): Future[Option[CommonArticle]] 
  
  def getArticleByUrl(url: String): Future[Option[CommonArticle]]
  
  def insertArticle(articleRequest: CommonCreateArticleRequest): Future[CommonCreateArticleResponse]
    
  def updateArticleStatus(request: CommonArticleUpdateStatusRequest): Future[CommonArticleUpdateStatusResponse]
  
  def getRecentArticles(request: CommonGetArticlesRequest): Future[List[CommonArticle]]
    
}

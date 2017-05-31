package com.yansoft.services.article

import scala.concurrent.Future

class ArticleServiceImpl extends ArticleService{
  
   import ArticleService._ 
  
  def getArticleById(id: Int): Future[Option[CommonArticle]] = ???
  
  def getArticleByUrl(url: String): Future[Option[CommonArticle]] = ???
  
  def insertArticle(articleRequest: CommonCreateArticleRequest): Future[CommonCreateArticleResponse] = ???
    
  def updateArticleStatus(request: CommonArticleUpdateStatusRequest): Future[CommonArticleUpdateStatusResponse] = ???
  
  def getRecentArticles(request: CommonGetArticlesRequest): Future[List[CommonArticle]] = ???
  
}
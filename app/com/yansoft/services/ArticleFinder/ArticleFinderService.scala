package com.yansoft.services.ArticleFinder

import com.yansoft.rss.ArticleFinder
import com.yansoft.utilities.ApplicationLogging

trait ArticleFinderService extends ApplicationLogging {
  
   def getActiveArticleFinders : List[ArticleFinder]
  
}
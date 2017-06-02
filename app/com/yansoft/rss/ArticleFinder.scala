package com.yansoft.rss

import scala.concurrent.Future
import com.yansoft.utilities.ApplicationLogging

/**
 * This should return a list of URLs
 */
trait ArticleFinder extends ApplicationLogging{
  def find():Future[List[String]]
}
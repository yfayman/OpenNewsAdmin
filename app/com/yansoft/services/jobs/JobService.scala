package com.yansoft.services.jobs

import scala.concurrent.Future
import com.yansoft.rss.ArticleFinder
import com.yansoft.utilities.ApplicationLogging

object JobService{
  case class TriggerJobRespose(success:Boolean)
}

trait JobService extends ApplicationLogging{
  
  import JobService._
  
  def scheduleJob(jobName:String):Unit 

  def triggerJob(jobName:String):Future[TriggerJobRespose]
  
  def loadAllJobs():Unit
  
}
package com.yansoft.services.jobs

import scala.concurrent.Future
import play.api.Configuration
import com.yansoft.jobs.Job
import com.yansoft.rss.ArticleFinder
import akka.actor.ActorSystem

class JobServiceImpl(config:Configuration, jobs:List[Job], jobActorSystem : ActorSystem) extends JobService{
  
  import JobService._
  
  def scheduleJob(jobName:String):Unit = ???

  def triggerJob(jobName:String):Future[TriggerJobRespose] = ???
  
  def loadAllJobs():Unit = ???
 
}
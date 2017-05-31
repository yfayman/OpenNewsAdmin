package com.yansoft.deadbolt

import be.objectify.deadbolt.scala.ExecutionContextProvider
import com.acadaca.fakenews.utilities.GlobalExecutionContextAware
import scala.concurrent.ExecutionContext
import org.slf4j.LoggerFactory

class OpenNewsDeadboltEc extends ExecutionContextProvider with GlobalExecutionContextAware {
  
  private val logger = LoggerFactory.getLogger(this.getClass)
  
  override def get(): ExecutionContext = {
    ec
  }
}
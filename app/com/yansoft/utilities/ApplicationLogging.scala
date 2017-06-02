package com.yansoft.utilities

trait ApplicationLogging {
  
  val logger = org.slf4j.LoggerFactory.getLogger(this.getClass)

}
package com.acadaca.fakenews.utilities

import com.yansoft.utilities.ConfiguredExecutionContexts


trait AkkaExecutionContextAware {
  implicit val ec = ConfiguredExecutionContexts.akkaExecutionContext
}
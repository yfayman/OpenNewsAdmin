package com.yansoft.services.security

import java.util.UUID;

class UUIDTokenGenerator extends TokenGenerator {
    
  override def generateToken():String = {
      UUID.randomUUID().toString();
   }
}
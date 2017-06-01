package com.yansoft.services.security

trait TokenGenerator {
  
    /**
     * Generates a random token
     */
    def generateToken():String;
}
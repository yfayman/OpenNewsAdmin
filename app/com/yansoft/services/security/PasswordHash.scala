package com.yansoft.services.security

trait PasswordHasher {
  def hash(password: String): String
}
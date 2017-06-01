package com.yansoft.services.security
import com.github.t3hnar.bcrypt._

class BCryptPasswordHash extends PasswordHasher {
  def hash(password: String): String = password.bcrypt
}
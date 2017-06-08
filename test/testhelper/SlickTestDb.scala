package testhelper

import org.scalatest.BeforeAndAfterAll
import org.scalatestplus.play.PlaySpec
import slick.jdbc.JdbcBackend._

trait SlickTestDb extends PlaySpec with BeforeAndAfterAll {
  
  implicit lazy val db = Database.forConfig("testdb")

  override def afterAll =  db.close()
  
}
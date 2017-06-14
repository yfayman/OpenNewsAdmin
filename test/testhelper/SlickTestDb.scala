package testhelper

import org.scalatest.{BeforeAndAfterAll,FlatSpec}
import org.scalatestplus.play.PlaySpec
import slick.jdbc.JdbcBackend._

trait SlickTestDb extends FlatSpec with BeforeAndAfterAll {

  implicit lazy val db = Database.forConfig("testdb")
  
  override def afterAll = {
    db.close()
  }

  override val invokeBeforeAllAndAfterAllEvenIfNoTestsAreExpected = true

}
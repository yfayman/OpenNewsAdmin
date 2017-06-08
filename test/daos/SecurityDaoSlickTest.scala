package daos

import play.api.Application
import org.scalatestplus.play.{ PlaySpec }
import scala.concurrent.duration.Duration
import scala.concurrent.Await
import java.util.Date
import scala.concurrent.Future
import com.yansoft.daos.security.SecurityDaoSlickImpl
import org.scalatest.BeforeAndAfterAll
import slick.jdbc.JdbcBackend._
import com.yansoft.daos.security.SecurityDaoSlickImpl
import testhelper.SlickTestDb
import slick.driver.PostgresDriver.api._

class SecurityDaoSlickTest extends SlickTestDb {

    lazy val dao = new SecurityDaoSlickImpl(db)
    
    "Create new user" should {
     val resultFuture =  dao.create("yantest", "password", "yfayman@gmail.com")
     val result = Await.result(resultFuture, Duration.Inf)
     assert(result.isSuccess)
     val rowsDeleted = dao.deleteAllUsers()
    }
    
    
  
}
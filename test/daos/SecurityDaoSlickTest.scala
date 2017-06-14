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

  override def beforeAll() = {
    dao.deleteAllUsers()
  }

  it should "Create a user successfully and then retrieve" in {

    val username = "yantest"
    val password = "password"
    val email = "yfayman@gmail.com"
    val resultFuture = dao.create(username, password, email)
    val createAccountResult = Await.result(resultFuture, Duration.Inf)
    assert(createAccountResult.isSuccess)

    val userId = createAccountResult.get.id
    val acctFuture = dao.getAccountById(userId)
    val getAcctResult = Await.result(acctFuture, Duration.Inf)
    assert(getAcctResult.isDefined)
    val acct = getAcctResult.get
    assert(acct.username == username)
    assert(acct.email == email)
    assert(acct.password == password)

  }

  it should "Fail to create a user with a taken email" in {
    val username = "yantest2"
    val password = "password2"
    val email = "yfayman@gmail.com"
    val resultFuture = dao.create(username, password, email)
    val createAccountResult = Await.result(resultFuture, Duration.Inf)
    assert(createAccountResult.isFailure)
  }

}
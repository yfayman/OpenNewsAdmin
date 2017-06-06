package com.yansoft.slick
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.PostgresDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Article.schema ++ ArticleStatus.schema ++ ExportStatus.schema ++ User.schema ++ UserType.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Article
   *  @param articleId Database column article_id SqlType(serial), AutoInc, PrimaryKey
   *  @param url Database column url SqlType(varchar), Length(256,true)
   *  @param title Database column title SqlType(varchar), Length(256,true)
   *  @param shortDescription Database column short_description SqlType(varchar), Length(256,true)
   *  @param html Database column html SqlType(text)
   *  @param articleStatusId Database column article_status_id SqlType(int4)
   *  @param userId Database column user_id SqlType(int4)
   *  @param createdDate Database column created_date SqlType(timestamp)
   *  @param updatedDate Database column updated_date SqlType(timestamp) */
  case class ArticleRow(articleId: Int, url: String, title: String, shortDescription: String, html: String, articleStatusId: Int, userId: Int, createdDate: java.sql.Timestamp, updatedDate: java.sql.Timestamp)
  /** GetResult implicit for fetching ArticleRow objects using plain SQL queries */
  implicit def GetResultArticleRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[ArticleRow] = GR{
    prs => import prs._
    ArticleRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[String], <<[Int], <<[Int], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table article. Objects of this class serve as prototypes for rows in queries. */
  class Article(_tableTag: Tag) extends Table[ArticleRow](_tableTag, "article") {
    def * = (articleId, url, title, shortDescription, html, articleStatusId, userId, createdDate, updatedDate) <> (ArticleRow.tupled, ArticleRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(articleId), Rep.Some(url), Rep.Some(title), Rep.Some(shortDescription), Rep.Some(html), Rep.Some(articleStatusId), Rep.Some(userId), Rep.Some(createdDate), Rep.Some(updatedDate)).shaped.<>({r=>import r._; _1.map(_=> ArticleRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column article_id SqlType(serial), AutoInc, PrimaryKey */
    val articleId: Rep[Int] = column[Int]("article_id", O.AutoInc, O.PrimaryKey)
    /** Database column url SqlType(varchar), Length(256,true) */
    val url: Rep[String] = column[String]("url", O.Length(256,varying=true))
    /** Database column title SqlType(varchar), Length(256,true) */
    val title: Rep[String] = column[String]("title", O.Length(256,varying=true))
    /** Database column short_description SqlType(varchar), Length(256,true) */
    val shortDescription: Rep[String] = column[String]("short_description", O.Length(256,varying=true))
    /** Database column html SqlType(text) */
    val html: Rep[String] = column[String]("html")
    /** Database column article_status_id SqlType(int4) */
    val articleStatusId: Rep[Int] = column[Int]("article_status_id")
    /** Database column user_id SqlType(int4) */
    val userId: Rep[Int] = column[Int]("user_id")
    /** Database column created_date SqlType(timestamp) */
    val createdDate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_date")
    /** Database column updated_date SqlType(timestamp) */
    val updatedDate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_date")

    /** Foreign key referencing ArticleStatus (database name article_article_status_id_fkey) */
    lazy val articleStatusFk = foreignKey("article_article_status_id_fkey", articleStatusId, ArticleStatus)(r => r.articleStatusId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name article_user_id_fkey) */
    lazy val userFk = foreignKey("article_user_id_fkey", userId, User)(r => r.userId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)

    /** Uniqueness Index over (url) (database name article_url_key) */
    val index1 = index("article_url_key", url, unique=true)
  }
  /** Collection-like TableQuery object for table Article */
  lazy val Article = new TableQuery(tag => new Article(tag))

  /** Entity class storing rows of table ArticleStatus
   *  @param articleStatusId Database column article_status_id SqlType(int4), PrimaryKey
   *  @param refCode Database column ref_code SqlType(varchar), Length(256,true) */
  case class ArticleStatusRow(articleStatusId: Int, refCode: String)
  /** GetResult implicit for fetching ArticleStatusRow objects using plain SQL queries */
  implicit def GetResultArticleStatusRow(implicit e0: GR[Int], e1: GR[String]): GR[ArticleStatusRow] = GR{
    prs => import prs._
    ArticleStatusRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table article_status. Objects of this class serve as prototypes for rows in queries. */
  class ArticleStatus(_tableTag: Tag) extends Table[ArticleStatusRow](_tableTag, "article_status") {
    def * = (articleStatusId, refCode) <> (ArticleStatusRow.tupled, ArticleStatusRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(articleStatusId), Rep.Some(refCode)).shaped.<>({r=>import r._; _1.map(_=> ArticleStatusRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column article_status_id SqlType(int4), PrimaryKey */
    val articleStatusId: Rep[Int] = column[Int]("article_status_id", O.PrimaryKey)
    /** Database column ref_code SqlType(varchar), Length(256,true) */
    val refCode: Rep[String] = column[String]("ref_code", O.Length(256,varying=true))

    /** Uniqueness Index over (refCode) (database name article_status_ref_code_key) */
    val index1 = index("article_status_ref_code_key", refCode, unique=true)
  }
  /** Collection-like TableQuery object for table ArticleStatus */
  lazy val ArticleStatus = new TableQuery(tag => new ArticleStatus(tag))

  /** Entity class storing rows of table ExportStatus
   *  @param exportStatusId Database column export_status_id SqlType(int4), PrimaryKey
   *  @param refCode Database column ref_code SqlType(varchar), Length(256,true) */
  case class ExportStatusRow(exportStatusId: Int, refCode: String)
  /** GetResult implicit for fetching ExportStatusRow objects using plain SQL queries */
  implicit def GetResultExportStatusRow(implicit e0: GR[Int], e1: GR[String]): GR[ExportStatusRow] = GR{
    prs => import prs._
    ExportStatusRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table export_status. Objects of this class serve as prototypes for rows in queries. */
  class ExportStatus(_tableTag: Tag) extends Table[ExportStatusRow](_tableTag, "export_status") {
    def * = (exportStatusId, refCode) <> (ExportStatusRow.tupled, ExportStatusRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(exportStatusId), Rep.Some(refCode)).shaped.<>({r=>import r._; _1.map(_=> ExportStatusRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column export_status_id SqlType(int4), PrimaryKey */
    val exportStatusId: Rep[Int] = column[Int]("export_status_id", O.PrimaryKey)
    /** Database column ref_code SqlType(varchar), Length(256,true) */
    val refCode: Rep[String] = column[String]("ref_code", O.Length(256,varying=true))

    /** Uniqueness Index over (refCode) (database name export_status_ref_code_key) */
    val index1 = index("export_status_ref_code_key", refCode, unique=true)
  }
  /** Collection-like TableQuery object for table ExportStatus */
  lazy val ExportStatus = new TableQuery(tag => new ExportStatus(tag))

  /** Entity class storing rows of table User
   *  @param userId Database column user_id SqlType(serial), AutoInc, PrimaryKey
   *  @param username Database column username SqlType(varchar), Length(256,true)
   *  @param email Database column email SqlType(varchar), Length(256,true)
   *  @param password Database column password SqlType(varchar), Length(256,true)
   *  @param userTypeId Database column user_type_id SqlType(int4)
   *  @param disabled Database column disabled SqlType(bool)
   *  @param authToken Database column auth_token SqlType(varchar), Length(256,true), Default(None)
   *  @param authExpiration Database column auth_expiration SqlType(timestamp), Default(None)
   *  @param createdDate Database column created_date SqlType(timestamp)
   *  @param updatedDate Database column updated_date SqlType(timestamp) */
  case class UserRow(userId: Int, username: String, email: String, password: String, userTypeId: Int, disabled: Boolean, authToken: Option[String] = None, authExpiration: Option[java.sql.Timestamp] = None, createdDate: java.sql.Timestamp, updatedDate: java.sql.Timestamp)
  /** GetResult implicit for fetching UserRow objects using plain SQL queries */
  implicit def GetResultUserRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Boolean], e3: GR[Option[String]], e4: GR[Option[java.sql.Timestamp]], e5: GR[java.sql.Timestamp]): GR[UserRow] = GR{
    prs => import prs._
    UserRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[Int], <<[Boolean], <<?[String], <<?[java.sql.Timestamp], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table user. Objects of this class serve as prototypes for rows in queries. */
  class User(_tableTag: Tag) extends Table[UserRow](_tableTag, "user") {
    def * = (userId, username, email, password, userTypeId, disabled, authToken, authExpiration, createdDate, updatedDate) <> (UserRow.tupled, UserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(userId), Rep.Some(username), Rep.Some(email), Rep.Some(password), Rep.Some(userTypeId), Rep.Some(disabled), authToken, authExpiration, Rep.Some(createdDate), Rep.Some(updatedDate)).shaped.<>({r=>import r._; _1.map(_=> UserRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7, _8, _9.get, _10.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column user_id SqlType(serial), AutoInc, PrimaryKey */
    val userId: Rep[Int] = column[Int]("user_id", O.AutoInc, O.PrimaryKey)
    /** Database column username SqlType(varchar), Length(256,true) */
    val username: Rep[String] = column[String]("username", O.Length(256,varying=true))
    /** Database column email SqlType(varchar), Length(256,true) */
    val email: Rep[String] = column[String]("email", O.Length(256,varying=true))
    /** Database column password SqlType(varchar), Length(256,true) */
    val password: Rep[String] = column[String]("password", O.Length(256,varying=true))
    /** Database column user_type_id SqlType(int4) */
    val userTypeId: Rep[Int] = column[Int]("user_type_id")
    /** Database column disabled SqlType(bool) */
    val disabled: Rep[Boolean] = column[Boolean]("disabled")
    /** Database column auth_token SqlType(varchar), Length(256,true), Default(None) */
    val authToken: Rep[Option[String]] = column[Option[String]]("auth_token", O.Length(256,varying=true), O.Default(None))
    /** Database column auth_expiration SqlType(timestamp), Default(None) */
    val authExpiration: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("auth_expiration", O.Default(None))
    /** Database column created_date SqlType(timestamp) */
    val createdDate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_date")
    /** Database column updated_date SqlType(timestamp) */
    val updatedDate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_date")

    /** Foreign key referencing UserType (database name user_user_type_id_fkey) */
    lazy val userTypeFk = foreignKey("user_user_type_id_fkey", userTypeId, UserType)(r => r.userTypeId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)

    /** Uniqueness Index over (email) (database name user_email_key) */
    val index1 = index("user_email_key", email, unique=true)
    /** Uniqueness Index over (password) (database name user_password_key) */
    val index2 = index("user_password_key", password, unique=true)
    /** Uniqueness Index over (username) (database name user_username_key) */
    val index3 = index("user_username_key", username, unique=true)
  }
  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))

  /** Entity class storing rows of table UserType
   *  @param userTypeId Database column user_type_id SqlType(int4), PrimaryKey
   *  @param refCode Database column ref_code SqlType(varchar), Length(256,true) */
  case class UserTypeRow(userTypeId: Int, refCode: String)
  /** GetResult implicit for fetching UserTypeRow objects using plain SQL queries */
  implicit def GetResultUserTypeRow(implicit e0: GR[Int], e1: GR[String]): GR[UserTypeRow] = GR{
    prs => import prs._
    UserTypeRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table user_type. Objects of this class serve as prototypes for rows in queries. */
  class UserType(_tableTag: Tag) extends Table[UserTypeRow](_tableTag, "user_type") {
    def * = (userTypeId, refCode) <> (UserTypeRow.tupled, UserTypeRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(userTypeId), Rep.Some(refCode)).shaped.<>({r=>import r._; _1.map(_=> UserTypeRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column user_type_id SqlType(int4), PrimaryKey */
    val userTypeId: Rep[Int] = column[Int]("user_type_id", O.PrimaryKey)
    /** Database column ref_code SqlType(varchar), Length(256,true) */
    val refCode: Rep[String] = column[String]("ref_code", O.Length(256,varying=true))

    /** Uniqueness Index over (refCode) (database name user_type_ref_code_key) */
    val index1 = index("user_type_ref_code_key", refCode, unique=true)
  }
  /** Collection-like TableQuery object for table UserType */
  lazy val UserType = new TableQuery(tag => new UserType(tag))
}

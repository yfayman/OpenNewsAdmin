import sbt.Keys._
import sbt._
import sbt.plugins.JvmPlugin

object Common extends AutoPlugin {
  override def trigger = allRequirements
  override def requires: sbt.Plugins = JvmPlugin

  override def projectSettings = Seq(
    organization := "com.yansoft",
    version := "1.0-SNAPSHOT",
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8", // yes, this is 2 args
      "-target:jvm-1.8",
      "-deprecation",
      "-feature",
      "-unchecked",
      "-Xlint",
      "-Yno-adapted-args",
      "-Ywarn-numeric-widen",
      "-Xfatal-warnings"),
    scalacOptions in Test ++= Seq("-Yrangepos"),
    autoAPIMappings := true)
}
object Settings {
  val jvmDependencies =
    Seq(
      "com.netaporter" %% "scala-uri" % "0.4.14",
      "com.typesafe.play" %% "play-slick" % "2.0.2",
      "com.typesafe.slick" %% "slick-codegen" % "3.1.0",
      "mysql" % "mysql-connector-java" % "6.0.5",
      "com.github.t3hnar" %% "scala-bcrypt" % "3.0",
      "commons-validator" % "commons-validator" % "1.4.0",
      "net.ruippeixotog" %% "scala-scraper" % "1.2.0",
      "com.googlecode.owasp-java-html-sanitizer" % "owasp-java-html-sanitizer" % "r239",
      "be.objectify" %% "deadbolt-scala" % "2.5.1",
      "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0" % "test",
      "org.mockito" % "mockito-core" % "2.7.22",
      "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
      "com.typesafe.slick" %% "slick-testkit" % "3.1.0" % "test")

}

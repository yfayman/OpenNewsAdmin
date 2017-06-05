import sbt.Keys._

scalaVersion := "2.11.11"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.yansoft.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.yansoft.binders._"


// The Play project itself
lazy val root = (project in file("."))
  .enablePlugins(Common, PlayScala)
  .settings(
	libraryDependencies ++= Settings.jvmDependencies,
	libraryDependencies += jdbc,
	libraryDependencies += filters,
	libraryDependencies += ws,
    name := """OpenNewsAdmin""",
    organization := "com.yansoft",
    version := "1.0-SNAPSHOT",
    testFrameworks := Seq(new TestFramework("com.novocode.junit.JUnitFramework")),
    testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v", "-s", "-a"),
    parallelExecution in Test := false,
    logBuffered := false,
    fork in run := true
  )


/*
// Slick Codegen Example
slick.codegen.SourceCodeGenerator.main(
  Array("slick.driver.PostgresDriver", "org.postgresql.Driver", "jdbc:postgresql://localhost:5432/dvdtest", "/home/yan/slick", "com.yansoft", "postgres", "postgres")
)


*/
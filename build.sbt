enablePlugins(ScalaJSPlugin)            // turn this project into a Scala.js project

name := "Hello ScalaJS 2"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint"
)

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.8.0",
  "be.doeraene" %%% "scalajs-jquery" % "0.8.0" % "test",  // scoped to be available ony for tests
  "com.lihaoyi" %%% "utest" % "0.3.1" % "test"
)

/* not necessary? */
//jsDependencies += "org.webjars" % "jquery" % "2.1.4" / "jquery.js" % "test"

skip in packageJSDependencies := false  // collect all JavaScript dependencies in one file

scalaJSStage in Global := FastOptStage  // to use Node.js or PhantomJS for tests

jsDependencies in Test += RuntimeDOM    // to use PhantomJS for tests so the DOM is available for the runtime

testFrameworks += new TestFramework("utest.runner.Framework")

persistLauncher in Compile := true      // so the main() is auto-detected and needs not to be included in the html

persistLauncher in Test := false        // no main() for tests

EclipseKeys.withSource := true

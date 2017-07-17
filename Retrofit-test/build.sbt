name := """Retrofit-test"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "com.squareup.retrofit2" % "retrofit" % "2.3.0",
  "com.squareup.retrofit2" % "converter-gson" % "2.3.0"
)

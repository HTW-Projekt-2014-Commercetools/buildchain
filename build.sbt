name := """buildchain"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)

parallelExecution in Global := false

instrumentSettings

ScoverageKeys.minimumCoverage := 70

ScoverageKeys.failOnMinimumCoverage := true
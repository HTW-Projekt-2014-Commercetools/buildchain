name := """buildchain"""

version := "1.0-SNAPSHOT"

org.scoverage.coveralls.CoverallsPlugin.coverallsSettings

scoverage.ScoverageSbtPlugin.instrumentSettings

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)
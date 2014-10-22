import scoverage.ScoverageSbtPlugin.instrumentSettings
import org.scoverage.coveralls.CoverallsPlugin.coverallsSettings

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

instrumentSettings

CoverallsPlugin.coverallsSettings

ScoverageKeys.minimumCoverage := 1

ScoverageKeys.failOnMinimumCoverage := true
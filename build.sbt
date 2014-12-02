name := """Paging-Api-Client"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.2"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

instrumentSettings

libraryDependencies += "io.sphere.sdk.jvm" % "sphere-play-sdk_2.10" % "1.0.0-M6" withSources()

libraryDependencies += "io.sphere.sdk.jvm" % "models" % "1.0.0-M7"

libraryDependencies += "org.elasticsearch" % "elasticsearch" % "1.4.1"

libraryDependencies += "com.softwaremill.macwire" %% "macros" % "0.7.3"

libraryDependencies += "com.softwaremill.macwire" %% "runtime" % "0.7.3"

parallelExecution in Test := false


//coverageMinimum := 70

//coverageFailOnMinimum := true
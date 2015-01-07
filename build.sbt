name := """Paging-Api-Client"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.4"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies += "io.sphere.sdk.jvm" % "models" % "1.0.0-M8"

libraryDependencies +=  "io.sphere.sdk.jvm" %% "play-2_3-java-client" % "1.0.0-M8"

libraryDependencies += "org.elasticsearch" % "elasticsearch" % "1.4.2"

libraryDependencies += "com.softwaremill.macwire" %% "macros" % "0.7.3"

libraryDependencies += "com.softwaremill.macwire" %% "runtime" % "0.7.3"

parallelExecution in Test := false


//coverageMinimum := 70

//coverageFailOnMinimum := true

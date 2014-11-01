name := """Paging-Api-Client"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.4"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

//libraryDependencies += "io.sphere.sdk.jvm" % "sphere-play-sdk_2.10" % "1.0.0-M7-SNAPSHOT" withSources()

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies += "io.sphere.sdk.jvm" % "sphere-play-sdk_2.10" % "1.0.0-M7-SNAPSHOT" withSources()


//libraryDependencies += "io.sphere.sdk.jvm" % "models" % "1.0.0-M6"

libraryDependencies += "com.softwaremill.macwire" %% "macros" % "0.7.3"

libraryDependencies += "com.softwaremill.macwire" %% "runtime" % "0.7.3"

parallelExecution in Test := false

instrumentSettings

//ScoverageKeys.minimumCoverage := 70

//ScoverageKeys.failOnMinimumCoverage := true
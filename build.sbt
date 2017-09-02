name := "ayumi-client"

lazy val commonSettings = Seq(
  organization := "shoma.me",
  version := "0.1.0",
  scalaVersion := "2.12.2"
)

lazy val `ayumi_client` = (project in file("."))
  .aggregate(model)
  .dependsOn(model)
  .settings(
    commonSettings
  )

lazy val model = (project in file("modules/model"))
  .settings(commonSettings)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

val circeVersion = "0.8.0"

libraryDependencies ++= Seq(
  "net.databinder.dispatch" %% "dispatch-core"          % "0.13.1",
  "io.circe"                %% "circe-core"             % circeVersion,
  "io.circe"                %% "circe-generic"          % circeVersion,
  "io.circe"                %% "circe-parser"           % circeVersion,
  "io.circe"                %% "circe-generic-extras"   % circeVersion,
  "org.scalatest"           % "scalatest_2.12"          % "3.0.4"         % "test",
  "org.mockito"             % "mockito-core"            % "2.7.22"        % "test"
)
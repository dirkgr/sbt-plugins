import bintray.{ Keys => BintrayKeys }
import bintray.{ Plugin => BintrayPlugin }

libraryDependencies ++= Seq("com.typesafe" % "config" % "1.2.0")

organization := "org.allenai.plugins"

name := "allenai-sbt-plugins"

lazy val ai2Plugins = project.in(file(".")).enablePlugins(ReleasePlugin)

scalacOptions := Seq(
  "-encoding", "utf8",
  "-feature",
  "-unchecked",
  "-deprecation",
  "-language:_",
  "-Xlog-reflective-calls")

// SBT requires 2.10 for now (1/15/15).
scalaVersion := "2.10.4"

sbtPlugin := true

// We wrap some 3rd party plugins:
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "0.7.6")

addSbtPlugin("com.github.gseitz" % "sbt-release" % "0.8.5")

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.6.0")

addSbtPlugin("com.danieltrinh" % "sbt-scalariform" % "1.3.0")

// Dependency graph visualiztion in SBT console
addSbtPlugin("com.gilt" % "sbt-dependency-graph-sugar" % "0.7.4")

// Wrapped by WebServicePlugin and WebappPlugin
addSbtPlugin("io.spray" % "sbt-revolver" % "0.7.2")

// Allows us to test our plugins via the sbt-scripted plugin:
scriptedSettings

BintrayPlugin.bintrayPublishSettings

publishMavenStyle := false

BintrayKeys.repository in BintrayKeys.bintray := "sbt-plugins"

BintrayKeys.bintrayOrganization in BintrayKeys.bintray := Some("allenai")

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html"))

// TODO(markschaake): sbtCliApp and other archetype plugins

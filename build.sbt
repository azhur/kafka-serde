
lazy val latest211 = "2.11.12"

lazy val latest212 = "2.12.12"

lazy val latest213 = "2.13.3"

lazy val `kafka-serde-scala` =
  project
    .in(file("."))
    .aggregate(
      `kafka-serde-avro4s`,
      `kafka-serde-circe`,
      `kafka-serde-jackson`,
      `kafka-serde-json4s`,
      `kafka-serde-jsoniter-scala`,
      `kafka-serde-play-json`,
      `kafka-serde-upickle`,
      `kafka-serde-scala-example`
    )
    .settings(commonSettings)
    .settings(scalafmtSettings)
    .settings(noPublishSettings)
    .settings(
      Compile / unmanagedSourceDirectories := Seq.empty,
      Test / unmanagedSourceDirectories    := Seq.empty
    )

lazy val `kafka-serde-circe` = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(commonSettings)
  .settings(scalafmtSettings)
  .settings(publishSettings)
  .settings(
    crossScalaVersions := Seq(latest213, latest212/*, latest211 Circe dropped Scala 2.11 support */),
    libraryDependencies ++= Seq(
      dependency.kafkaClients,
      dependency.circe,
      dependency.circeParser,
      dependency.circeJawn,
      dependency.circeGeneric % Test,
      dependency.scalaTest    % Test
    )
  )

lazy val `kafka-serde-json4s` = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(commonSettings)
  .settings(scalafmtSettings)
  .settings(publishSettings)
  .settings(
    crossScalaVersions := Seq(latest213, latest212, latest211),
    libraryDependencies ++= Seq(
      dependency.kafkaClients,
      dependency.json4sCore,
      dependency.json4sJackson % Test,
      dependency.json4sNative  % Test,
      dependency.scalaTest     % Test
    )
  )

lazy val `kafka-serde-jsoniter-scala` = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(commonSettings)
  .settings(scalafmtSettings)
  .settings(publishSettings)
  .settings(
    crossScalaVersions := Seq(latest213, latest212, latest211),
    libraryDependencies ++= Seq(
      dependency.kafkaClients,
      dependency.jsoniterScalaCore,
      dependency.jsoniterScalaMacros % Test,
      dependency.scalaTest           % Test
    )
  )

lazy val `kafka-serde-play-json` = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(commonSettings)
  .settings(scalafmtSettings)
  .settings(publishSettings)
  .settings(
    crossScalaVersions := Seq(latest213, latest212/*, latest211 Play-JSON dropped Scala 2.11 support */),
    libraryDependencies ++= Seq(
      dependency.kafkaClients,
      dependency.playJson,
      dependency.scalaTest     % Test
    )
  )

lazy val `kafka-serde-upickle` = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(commonSettings)
  .settings(scalafmtSettings)
  .settings(publishSettings)
  .settings(
    crossScalaVersions := Seq(latest213, latest212/*, latest211 uPickle dropped Scala 2.11 support */),
    libraryDependencies ++= Seq(
      dependency.kafkaClients,
      dependency.upickle,
      dependency.scalaTest     % Test
    )
  )

lazy val `kafka-serde-avro4s` = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(commonSettings)
  .settings(scalafmtSettings)
  .settings(publishSettings)
  .settings(
    crossScalaVersions := Seq(latest213, latest212),
    libraryDependencies ++= Seq(
      dependency.kafkaClients,
      dependency.avro4sCore,
      dependency.avro4sKafka,
      dependency.scalaTest     % Test
    )
  )

lazy val `kafka-serde-jackson` = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(commonSettings)
  .settings(scalafmtSettings)
  .settings(publishSettings)
  .settings(
    crossScalaVersions := Seq(latest213, latest212, latest211),
    libraryDependencies ++= Seq(
      dependency.kafkaClients,
      dependency.jacksonCore,
      dependency.jacksonScala,
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      dependency.jacksonProtobuf % Test,
      dependency.jacksonAvro     % Test,
      dependency.scalaTest       % Test
    )
  )

lazy val `kafka-serde-scala-example` = project
  .dependsOn(`kafka-serde-circe`)
  .enablePlugins(AutomateHeaderPlugin)
  .settings(commonSettings)
  .settings(scalafmtSettings)
  .settings(noPublishSettings)
  .settings(
    crossScalaVersions := Seq(latest213, latest212/*, latest211 Circe dropped Scala 2.11 support */),
    libraryDependencies ++= Seq(
      dependency.kafkaStreamsScala,
      dependency.circeGeneric,
      dependency.scalaTest        % Test
    )
  )

lazy val dependency =
  new {
    object Version {
      val avro4s                        = "4.0.0"
      val circe                         = "0.13.0"
      val json4s                        = "3.6.9"
      val jsoniterScala                 = "2.6.0"
      val scalaTest                     = "3.2.2"
      val kafka                         = "2.6.0"
      val play                          = "2.9.0"
      val upickle                       = "1.2.0"
      val jackson                       = "2.11.2"
    }
    val kafkaClients        = "org.apache.kafka"                      %  "kafka-clients"                    % Version.kafka
    val kafkaStreamsScala   = "org.apache.kafka"                      %% "kafka-streams-scala"              % Version.kafka
    val avro4sCore          = "com.sksamuel.avro4s"                   %% "avro4s-core"                      % Version.avro4s
    val avro4sKafka         = "com.sksamuel.avro4s"                   %% "avro4s-kafka"                     % Version.avro4s
    val circe               = "io.circe"                              %% "circe-core"                       % Version.circe
    val circeParser         = "io.circe"                              %% "circe-parser"                     % Version.circe
    val circeJawn           = "io.circe"                              %% "circe-jawn"                       % Version.circe
    val circeGeneric        = "io.circe"                              %% "circe-generic"                    % Version.circe
    val json4sCore          = "org.json4s"                            %% "json4s-core"                      % Version.json4s
    val json4sJackson       = "org.json4s"                            %% "json4s-jackson"                   % Version.json4s
    val json4sNative        = "org.json4s"                            %% "json4s-native"                    % Version.json4s
    val jsoniterScalaCore   = "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core"              % Version.jsoniterScala
    val jsoniterScalaMacros = "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros"            % Version.jsoniterScala
    val playJson            = "com.typesafe.play"                     %% "play-json"                        % Version.play
    val upickle             = "com.lihaoyi"                           %% "upickle"                          % Version.upickle
    val jacksonScala        = "com.fasterxml.jackson.module"          %% "jackson-module-scala"             % Version.jackson
    val jacksonCore         = "com.fasterxml.jackson.core"            %  "jackson-core"                     % Version.jackson
    val jacksonProtobuf     = "com.fasterxml.jackson.dataformat"      %  "jackson-dataformat-protobuf"      % Version.jackson
    val jacksonAvro         = "com.fasterxml.jackson.dataformat"      %  "jackson-dataformat-avro"          % Version.jackson
    val scalaTest           = "org.scalatest"                         %% "scalatest"                        % Version.scalaTest
  }


lazy val settings =
  commonSettings ++
  scalafmtSettings ++
  publishSettings

lazy val commonSettings =
  Seq(
    resolvers += "Sonatype OSS Staging" at "https://oss.sonatype.org/content/repositories/staging",
    scalaVersion := latest213,
    homepage := Some(url("https://github.com/azhur/kafka-serde-scala")),
    organization := "io.github.azhur",
    organizationName := "Artur Zhurat",
    organizationHomepage := Some(url("https://github.com/azhur")),
    startYear := Some(2018),
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding", "UTF-8"
    ),
    developers := List(
      Developer(
        id = "azhur",
        name = "Artur Zhurat",
        email = "artur.zhurat@gmail.com",
        url = url("https://twitter.com/a_zhur")
      )
    )
  )

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := true
  )


lazy val noPublishSettings = Seq(
  skip in publish := true,
  publishTo := Some(if (isSnapshot.value) Opts.resolver.sonatypeSnapshots else Opts.resolver.sonatypeStaging)
)

lazy val publishSettings = Seq(
  publishTo := Some(if (isSnapshot.value) Opts.resolver.sonatypeSnapshots else Opts.resolver.sonatypeStaging),
  sonatypeProfileName := "io.github.azhur",
  homepage := Some(url("https://github.com/azhur/kafka-serde-scala")),
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/azhur/kafka-serde-scala"),
      "scm:git@github.com:azhur/kafka-serde-scala.git"
    )
  ),
  publishMavenStyle := true,
  pomIncludeRepository := { _ => false }
)
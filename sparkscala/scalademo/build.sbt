name := "neo-spark-examples"

version := "0.1.0"

//scalaVersion := "2.12.8"
scalaVersion := "2.11.12"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

dependencyOverrides ++= Seq(
  "com.fasterxml.jackson.core" % "jackson-core" % "2.8.7",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.7",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.8.7",
)

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.4.0" % "provided",
  "org.apache.spark" %% "spark-sql" % "2.4.0",
  "org.apache.spark" %% "spark-hive" % "2.4.0",
  "org.apache.spark" %% "spark-mllib" % "2.4.0",
  "org.apache.spark" %% "spark-streaming" % "2.4.0",
  "org.apache.spark" %% "spark-streaming-flume" % "2.3.2",
  "org.apache.spark" %% "spark-streaming-kafka-0-8" % "2.4.0",
  "com.datastax.spark" %% "spark-cassandra-connector" % "2.4.0",
  "org.eclipse.jetty" % "jetty-client" % "9.4.14.v20181114",
  "com.twitter.elephantbird" % "elephant-bird" % "4.17",
  "com.twitter.elephantbird" % "elephant-bird-core" % "4.17",
  "com.typesafe.play" % "play-json_2.11" % "2.6.13",
)

resolvers ++= Seq(
  "Local Maven Repository" at "file:///C:/Users/Hansen/.m2/repository",
)


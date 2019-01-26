name := "neo-spark-examples"

version := "0.1.0"

//scalaVersion := "2.12.8"
scalaVersion := "2.11.12"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.4.0" % "provided",
  "org.apache.spark" %% "spark-sql" % "2.4.0",
  "org.apache.spark" %% "spark-hive" % "2.4.0",
  "org.apache.spark" %% "spark-streaming" % "2.4.0",
  "org.apache.spark" %% "spark-mllib" % "2.4.0",
  "com.datastax.spark" %% "spark-cassandra-connector" % "2.4.0",
  "org.eclipse.jetty" % "jetty-client" % "9.4.14.v20181114",
  "com.twitter.elephantbird" % "elephant-bird" % "4.17",
  "com.twitter.elephantbird" % "elephant-bird-core" % "4.17",
  "com.typesafe.play" % "play-json_2.11" % "2.6.13",
)


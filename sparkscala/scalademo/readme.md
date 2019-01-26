# About
This is a code example that complements the material in the Spark O'Reilly book. 


# How to build
1.下载并配置好jdk-8
2.下载并配置好scala-2.11.x，部分库只支持到2.11
3.下载并配置好sbt-1.2.7


#配置sbt-eclipse
## 新建工程
### 构建如下目录
'''shell
    sparkscalademos
    ├─build.sbt
    │
    ├─project
    │  ├─build.properties
    │  └─plugins.sbt
    │
    └─src
       └─main
           └─scala
'''

### build.sbt内容如下

'''
	name := "neo-spark-examples"
	
	version := "1.0.0"
	
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

'''

### build.sbt内容如下
'''
	addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.2.4")
'''

### build.properties内容如下
'''
	sbt.version=1.2.7
'''

## 生成eclipse工程
'''shell
sbt eclipse
'''

## 打开工程
1.下载并配置scala ide for eclipse 
2.打开scala ide for eclipse，导入已有工程


# generate protobuf class
'''shell
#need protobuf2.x，sbt默认导入的jar包为2.5
protoc.exe --java_out . places.proto
protoc.exe --java_out . address_book.proto
'''
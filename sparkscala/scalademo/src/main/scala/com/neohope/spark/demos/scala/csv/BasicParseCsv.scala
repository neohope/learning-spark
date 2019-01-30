package com.neohope.spark.demos.scala.csv

import java.io.StringReader
import java.io.StringWriter
import org.apache.spark._
import scala.collection.JavaConversions._
import au.com.bytecode.opencsv.CSVReader
import au.com.bytecode.opencsv.CSVWriter
import scala.Iterator

/**
 * 计算CSV并过滤
 */
object BasicParseCsv {
  case class Person(name: String, favouriteAnimal: String)

  val sc = new SparkContext("local", "BasicParseCsv", System.getenv("SPARK_HOME"))

  def parseCsv(inputFile: String, outputFile: String) = {
    val input = sc.textFile(inputFile)
    val result = input.map { line =>
      val reader = new CSVReader(new StringReader(line));
      reader.readNext();
    }

    val people = result.map(x => Person(x(0), x(1)))
    val pandaLovers = people.filter(person => person.favouriteAnimal == "panda")
    val ret = pandaLovers.collect()
    
    pandaLovers.map(person => List(person.name, person.favouriteAnimal).toArray)
    .mapPartitions { people =>
      val stringWriter = new StringWriter();
      val csvWriter = new CSVWriter(stringWriter);
      csvWriter.writeAll(people.toList)
      Iterator(stringWriter.toString)
    }.saveAsTextFile(outputFile)
    
    ret
  }

  def main(args: Array[String]) {
    val inputFile = "../../datain/favourite_animals.csv"
    val outputFile = "../../dataout/basicparsecsv.scala"
    val ans = parseCsv(inputFile,outputFile)
    ans.foreach(println)
  }
}

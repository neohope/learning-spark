package com.neohope.spark.demos.scala.csv

import java.io.StringReader
import org.apache.spark._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import scala.collection.JavaConversions._
import au.com.bytecode.opencsv.CSVReader

/**
 * 加载CSV文件
 */
object BasicParseWholeFileCsv {
  val sc = new SparkContext("local", "BasicParseWholeFileCsv")

  def parseCsv(inputFile: String) = {
    val input = sc.wholeTextFiles(inputFile)
    val result = input.flatMap {
      case (_, txt) =>
        val reader = new CSVReader(new StringReader(txt));
        reader.readAll()
    }
    val ans = result.collect().map(_.toList).mkString(",")
    ans
  }

  def main(args: Array[String]) {
    val inputFile = "../../datain/favourite_animals.csv"
    val result = parseCsv(inputFile)
    println(result)
  }
}

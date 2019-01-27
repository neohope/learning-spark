package com.neohope.spark.demos.scala

import org.apache.spark._
import org.apache.spark.SparkContext._

/**
 * 计算给定目录中，计算每个文件中数字的平均值
 */
object BasicAvgFromFiles {

  val sc = new SparkContext("local", "BasicAvgFromFiles")

  def computeAvg(inputFolder: String) = {
    val input = sc.wholeTextFiles(inputFolder)
    val result = input
    .mapValues{v=>
      val nums = v.split("\r\n").map(_.toInt)
      nums.sum/nums.size.toDouble
    }
    result
  }

  def main(args: Array[String]) {
    val inputFolder = "../../datain/nums/"
    val result = computeAvg(inputFolder)
    result.foreach(println)
  }
}

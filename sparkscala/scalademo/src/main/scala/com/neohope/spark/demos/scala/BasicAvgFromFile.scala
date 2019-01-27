package com.neohope.spark.demos.scala

import org.apache.spark._

/**
 * 计算给定文件中数字的平均值
 */
object BasicAvgFromFile {
  val sc = new SparkContext("local", "BasicAvg")
  
  def computeAvg(inputFile: String) = {
    val input = sc.textFile(inputFile)
    val result = input.map(_.toInt).aggregate((0, 0))(
      (acc, value) => (acc._1 + value, acc._2 + 1),
      (acc1, acc2) => (acc1._1 + acc2._1, acc1._2 + acc2._2))
    val avg = result._1 / result._2.toFloat
    avg
  }

  def main(args: Array[String]) {
    val inputFile = "../../datain/nums.txt" 
    val avg = computeAvg(inputFile)
    println(avg)
  }
}

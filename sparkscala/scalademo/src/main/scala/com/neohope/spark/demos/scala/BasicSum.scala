package com.neohope.spark.demos.scala

import org.apache.spark._

/**
 * 求和
 */
object BasicSum {
  val sc = new SparkContext("local", "BasicMap")

  def computeSum(nums: List[Int]) = {
    val input = sc.parallelize(nums)
    val sum = input.fold(0)((x, y) => (x + y))
    sum
  }

  def main(args: Array[String]) {
    val nums = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    val sum = computeSum(nums)
    println(sum)
  }
}

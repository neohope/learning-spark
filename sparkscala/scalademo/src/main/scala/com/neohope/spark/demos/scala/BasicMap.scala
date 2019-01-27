package com.neohope.spark.demos.scala

import org.apache.spark._

/**
 * 计算平均值
 */
object BasicMap {

  val sc = new SparkContext("local", "BasicMap")

  def computeSqure(nums: List[Int]) = {
    val result = sc.parallelize(nums).map(x => x * x).collect()
    result
  }

  def main(args: Array[String]) {
    val nums = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    val result = computeSqure(nums)
    println(result.mkString(","))
  }
}

package com.neohope.spark.demos.scala

import org.apache.spark._
import org.apache.spark.rdd.RDD

/**
 * 计算平均值
 */
object BasicAvg {
  val sc = new SparkContext("local", "BasicAvg")

  def computeSum(nums: List[Int]) = {
    val rdd = sc.parallelize(nums)

    //两个函数分别为seqOP, combOp
    rdd.aggregate((0, 0))(
      (x, y) => (x._1 + y, x._2 + 1),
      (x, y) => (x._1 + y._1, x._2 + y._2))
  }

  def computeAvg(nums: List[Int]) = {
    val result = computeSum(nums)
    val avg = result._1 / result._2.toFloat
    avg
  }

  def main(args: Array[String]) {
    val nums = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    val avg = computeAvg(nums)
    println(avg)
  }
}

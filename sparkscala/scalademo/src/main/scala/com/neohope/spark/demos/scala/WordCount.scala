package com.neohope.spark.demos.scala

import org.apache.spark._
import org.apache.spark.SparkContext._

/**
 * 单词出现频率统计
 */

object WordCount {
  
  val sc = new SparkContext("local", "WordCount")
  
  def countString(lines: List[String]){
    val input = sc.parallelize(lines)
    val words = input.flatMap(line => line.split(" "))
    val wc = words.countByValue()
    println(wc.mkString(","))
  }

  def countFile(fileIn: String, fileOut: String){
    val input = sc.textFile(fileIn)
    val words = input.flatMap(line => line.split(" "))
    val counts = words.map(word => (word, 1)).reduceByKey { case (x, y) => x + y }
    counts.saveAsTextFile(fileOut)
  }
  
  def main(args: Array[String]) {
    val lines=List("pandas", "i like pandas", "i am neohope")
    countString(lines)
    
    val fileIn="../../datain/TDOI.txt"
    val fileOut="../../dataout/wordcount.scala"
    countFile(fileIn, fileOut)
  }
}
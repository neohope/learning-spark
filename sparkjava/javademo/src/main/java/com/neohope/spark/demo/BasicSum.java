package com.neohope.spark.demo;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * 平均值计算
 */
public class BasicSum {
	JavaSparkContext sc = new JavaSparkContext("local", "basicsum");

	public Integer calcSum(List<Integer> nums) {
		JavaRDD<Integer> rdd = sc.parallelize(nums);
		Integer result = rdd.fold(0, new BasicSumFunctions.Sum());
		return result;
	}

	public static void main(String[] args) throws Exception {
		Integer[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		BasicSum basicSum = new BasicSum();
		Integer sum = basicSum.calcSum(Arrays.asList(nums));
		System.out.println(sum);
	}
}

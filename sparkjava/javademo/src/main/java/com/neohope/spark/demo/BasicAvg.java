package com.neohope.spark.demo;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * 平均值计算
 */
public final class BasicAvg {
	JavaSparkContext sc = new JavaSparkContext("local", "basicavg");

	public float calcAvg(List<Integer> nums){
		JavaRDD<Integer> rdd = sc.parallelize(nums);
		BasicAvgFunctions.AvgCountBean initial = new BasicAvgFunctions.AvgCountBean(0, 0);
		BasicAvgFunctions.AvgCountBean result = rdd.aggregate(initial, 
				new BasicAvgFunctions.AddAndCount(), 
				new BasicAvgFunctions.Combine());
		sc.stop();
		return result.avg();
	}

	public static void main(String[] args) throws Exception {
		Integer[] nums={0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		BasicAvg basicAvg=new BasicAvg();
		float avg = basicAvg.calcAvg(Arrays.asList(nums));
		System.out.println(avg);
	}
}

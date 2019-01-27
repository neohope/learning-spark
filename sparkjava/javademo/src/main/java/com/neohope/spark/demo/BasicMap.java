package com.neohope.spark.demo;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * 平均值计算
 */
public class BasicMap {
	JavaSparkContext sc = new JavaSparkContext("local", "basicmap");
	
	public List<Integer> calcAvg(List<Integer> nums){
		JavaRDD<Integer> rdd = sc.parallelize(nums)
				.map(new BasicMapFunctions.Squre());
		return rdd.collect();
	}

	public static void main(String[] args) throws Exception {
		Integer[] nums={0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		BasicMap basicMap=new BasicMap();
		List<Integer> result=basicMap.calcAvg(Arrays.asList(nums));
		System.out.println(StringUtils.join(result, ","));
	}
}

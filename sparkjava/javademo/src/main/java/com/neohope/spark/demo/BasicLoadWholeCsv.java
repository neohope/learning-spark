package com.neohope.spark.demo;

import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.commons.lang.StringUtils;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * 加载CSV，并增加过滤条件
 */
public class BasicLoadWholeCsv {

	JavaSparkContext sc = new JavaSparkContext("local", "loadwholecsv");

	public List<String[]> filter(String csv1) throws Exception {
		JavaPairRDD<String, String> csvData = sc.wholeTextFiles(csv1);
		JavaRDD<String[]> keyedRDD = csvData.flatMap(new BasicLoadWholeCsvFunctions.ParseLine());
		JavaRDD<String[]> result = keyedRDD.filter(new BasicLoadWholeCsvFunctions.FilterLine());
		return result.collect();
	}

	public static void main(String[] args) throws Exception {
		String csvInput = "../../datain/favourite_animals.csv";
		BasicLoadWholeCsv filter = new BasicLoadWholeCsv();
		List<String[]> result = filter.filter(csvInput);
		for (String[] row : result) {
			System.out.println(StringUtils.join(row, ","));
		}
	}
}

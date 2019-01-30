package com.neohope.spark.demo.csv;

import java.util.List;
import scala.Tuple2;

import org.apache.spark.api.java.JavaRDD;
import org.apache.commons.lang.StringUtils;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * 两个CSV做Join
 */
public class BasicJoinCsv {

	JavaSparkContext sc = new JavaSparkContext("local", "basicjoincsv");

	public List<Tuple2<Integer, Tuple2<String[], String[]>>> join(String csv1, String csv2) throws Exception {
		JavaRDD<String> csvFile1 = sc.textFile(csv1);
		JavaRDD<String> csvFile2 = sc.textFile(csv2);
		JavaPairRDD<Integer, String[]> keyedRDD1 = csvFile1.mapToPair(new BasicJoinCsvFunctions.ParseLine());
		JavaPairRDD<Integer, String[]> keyedRDD2 = csvFile2.mapToPair(new BasicJoinCsvFunctions.ParseLine());
		JavaPairRDD<Integer, Tuple2<String[], String[]>> result = keyedRDD1.join(keyedRDD2);
		List<Tuple2<Integer, Tuple2<String[], String[]>>> resultCollection = result.collect();
		return resultCollection;
	}

	public static void main(String[] args) throws Exception {
		String csv1 = "../../datain/int_string.csv";
		String csv2 = "../../datain/int_string.csv";
		BasicJoinCsv jsv = new BasicJoinCsv();
		List<Tuple2<Integer, Tuple2<String[], String[]>>> result = jsv.join(csv1, csv2);
		for (Tuple2<Integer, Tuple2<String[], String[]>> x : result) {
			Tuple2<String[], String[]> y = x._2;
			System.out.println(x._1+"\t"+StringUtils.join(y._1, ",")+"\t"+StringUtils.join(y._2, ","));
		}
	}
}

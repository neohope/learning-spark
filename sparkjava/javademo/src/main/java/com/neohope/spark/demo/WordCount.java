package com.neohope.spark.demo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import scala.Tuple2;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * 单词出现频率统计
 */
public class WordCount implements Serializable{
	private static final long serialVersionUID = 1L;
	
	JavaSparkContext sc;
	
	public WordCount(){
		sc = new JavaSparkContext("local", "wordcount");
	}
	
	public void countString(List<String> lines){
		JavaRDD<String> rdd = sc.parallelize(lines);
		JavaPairRDD<String, Integer> counts = rdd
				.flatMap(new WordCountFunctions.WcFlactMapFunction())
				.mapToPair(new WordCountFunctions.WcPairFunction())
				.reduceByKey(new WordCountFunctions.WcFunction2());
		
		for(Tuple2<String,Integer> t : counts.collect()){
			System.out.println(t._1()+"\t"+t._2);
		}
	}
	
	public void countFile(String fileIn, String fileOut){
		JavaRDD<String> rdd = sc.textFile(fileIn);
		
		JavaPairRDD<String, Integer> counts = rdd
			.flatMap(new WordCountFunctions.WcFlactMapFunction())
			.mapToPair(new WordCountFunctions.WcPairFunction())
			.reduceByKey(new WordCountFunctions.WcFunction2());
		counts.saveAsTextFile(fileOut);
		
		sc.close();
	}
	
	public static void main(String[] args) throws Exception {
		WordCount wc=new WordCount();
		
		String[] lines={"pandas", "i like pandas", "i am neohope"};
		wc.countString(Arrays.asList(lines));
		
		String fileIn="../../datain/TDOI.txt";
		String fileOut="../../dataout/wordcount.java";
		wc.countFile(fileIn, fileOut);
	}
}

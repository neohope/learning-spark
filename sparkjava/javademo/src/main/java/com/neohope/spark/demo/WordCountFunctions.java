package com.neohope.spark.demo;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

/**
 * 内部类或匿名类序列化时，要求外部类及外部类全部成员都可以序列化
 */
public class WordCountFunctions {
	
	public static final class WcFlactMapFunction implements FlatMapFunction<String, String> {
		private static final long serialVersionUID = 1L;
		public Iterator<String> call(String x) {
			return Arrays.asList(x.split(" ")).iterator();
		}
	}
	
	public static final class WcPairFunction implements PairFunction<String, String, Integer> {
		private static final long serialVersionUID = 1L;
		public Tuple2<String, Integer> call(String x) {
			return new Tuple2<>(x, 1);
		}
	}
	
	public static final class WcFunction2 implements Function2<Integer, Integer, Integer> {
		private static final long serialVersionUID = 1L;
		public Integer call(Integer x, Integer y) {
			return x + y;
		}
	}
}

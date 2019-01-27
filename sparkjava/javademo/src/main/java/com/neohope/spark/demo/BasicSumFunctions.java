package com.neohope.spark.demo;

import org.apache.spark.api.java.function.Function2;

public class BasicSumFunctions {
	public static final class Sum implements Function2<Integer, Integer, Integer> {
		private static final long serialVersionUID = 1L;

		public Integer call(Integer x, Integer y) {
			return x + y;
		}
	}
}

package com.neohope.spark.demo;

import org.apache.spark.api.java.function.Function;

public class BasicMapFunctions {
	public static class Squre implements Function<Integer, Integer> {
		private static final long serialVersionUID = 1L;
		public Integer call(Integer x) {
			return x * x;
		}
	}
}

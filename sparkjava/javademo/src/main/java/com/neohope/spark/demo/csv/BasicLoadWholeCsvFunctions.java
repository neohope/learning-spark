package com.neohope.spark.demo.csv;

import java.io.StringReader;
import java.util.Iterator;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;

import au.com.bytecode.opencsv.CSVReader;
import scala.Tuple2;

public class BasicLoadWholeCsvFunctions {
	public static class ParseLine implements FlatMapFunction<Tuple2<String, String>, String[]> {
		private static final long serialVersionUID = 1L;

		@SuppressWarnings("resource")
		public Iterator<String[]> call(Tuple2<String, String> file) throws Exception {
			CSVReader reader = new CSVReader(new StringReader(file._2()));
			return reader.readAll().iterator();
		}
	}
	
	final static String key = "spark";
	public static class FilterLine implements Function<String[], Boolean> {
		private static final long serialVersionUID = 1L;

		public Boolean call(String[] input) {
			return input[0].equals(key);
		}
	}
}

package com.neohope.spark.demo.csv;

import java.io.StringReader;

import org.apache.spark.api.java.function.PairFunction;

import au.com.bytecode.opencsv.CSVReader;
import scala.Tuple2;

public class BasicJoinCsvFunctions {
	public static class ParseLine implements PairFunction<String, Integer, String[]> {
		private static final long serialVersionUID = 1L;

		@SuppressWarnings("resource")
		public Tuple2<Integer, String[]> call(String line) throws Exception {
			CSVReader reader = new CSVReader(new StringReader(line));
			String[] elements = reader.readNext();
			Integer key = Integer.parseInt(elements[0]);
			return new Tuple2<>(key, elements);
		}
	}
}

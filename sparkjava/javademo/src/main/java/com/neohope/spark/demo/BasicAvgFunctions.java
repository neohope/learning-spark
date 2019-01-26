package com.neohope.spark.demo;

import java.io.Serializable;

import org.apache.spark.api.java.function.Function2;

public class BasicAvgFunctions {
	public static class AvgCountBean implements Serializable {
		private static final long serialVersionUID = 1L;

		public AvgCountBean(int total, int num) {
			total_ = total;
			num_ = num;
		}

		public int total_;
		public int num_;

		public float avg() {
			return total_ / (float) num_;
		}
	}
	
	public static final class AddAndCount implements Function2<AvgCountBean, Integer, AvgCountBean> {
		private static final long serialVersionUID = 1L;

		@Override
		public AvgCountBean call(AvgCountBean a, Integer x) {
			a.total_ += x;
			a.num_ += 1;
			return a;
		}
	};
	
	public static final class Combine implements Function2<AvgCountBean, AvgCountBean, AvgCountBean> {
		private static final long serialVersionUID = 1L;
		
		@Override
		public AvgCountBean call(AvgCountBean a, AvgCountBean b) {
			a.total_ += b.total_;
			a.num_ += b.num_;
			return a;
		}
	};
}

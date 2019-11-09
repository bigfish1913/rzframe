package com.rz.frame.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Map;
import java.util.Random;

public class RandomUtils {
	
	public static String getNumber(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			Random random = new Random();
			int i1 = random.nextInt(10);
			sb.append(i1);
		}
		return sb.toString();
	}
	
	public static long getNumberLong(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			Random random = new Random();
			int i1 = random.nextInt(10);
			sb.append(i1);
		}
		return Long.parseLong(sb.toString());
	}
	
	public static long getNumber(int min, int max) {
		
		Random random = new Random(System.currentTimeMillis());
		long result = random.nextInt(max) % (max - min + 1) + min;
		return result;
	}
	
	public static int getNumberInt(int min, int max) {
		
		Random random = new Random(System.currentTimeMillis());
		int result = random.nextInt(max) % (max - min + 1) + min;
		return result;
	}
	
	public static double getDoubleNumber(int intBit, int pointBit) {
		
		return (double) Math.round(Math.random() * intBit * Math.pow(10, pointBit)) / Math.pow(10, pointBit);
		
	}
	public static String getRandomString(int length){
		String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random=new Random();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<length;i++){
			int number=random.nextInt(62);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}
}

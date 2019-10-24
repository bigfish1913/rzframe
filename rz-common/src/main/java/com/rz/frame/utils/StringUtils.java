package com.rz.frame.utils;

public class StringUtils {
	public static boolean isEmpty(String value) {
		return value == null || value.equals("");
	}
	
	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}
	public static boolean isBlank(String value) {
		return value.trim().equals("");
	}
}

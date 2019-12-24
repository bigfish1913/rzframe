package com.rz.frame.utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {
	public static boolean isEmpty(String value) {
		return value == null || value.length() == 0;
	}
	
	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}
	
	public static boolean isBlank(String value) {
		return value.trim().equals("");
	}
	
	public static boolean isContain(String source, String target, String split) {
		if (source == null || target == null) {
			return false;
		}
		List<String> sourceList = Arrays.asList(source.split(split));
		return sourceList.contains(target);
		
	}
	
	public static boolean isContain(String source, String target) {
		return isContain(source, target, ",");
	}
}

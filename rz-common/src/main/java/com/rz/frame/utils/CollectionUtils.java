package com.rz.frame.utils;

import java.util.List;

public class CollectionUtils {
	
	public static boolean isEmpty(List objects){
		return objects==null||objects.size()==0;
	}
	public static boolean isNotEmpty(List objects){
		return !isEmpty(objects);
	}
}

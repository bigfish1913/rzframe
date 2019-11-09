package com.rz.frame.utils;

import org.apache.log4j.Logger;

public class RzLogger {
	private static final Logger LOGGER = Logger.getLogger(RzLogger.class);
	
	public static void infoObject(Object content){
		 info(content.getClass().getSimpleName(),":",JsonUtils.serializeObject(content));
	}
	public static void info(String content){
		LOGGER.info(content);
	}
	public static void info(String ... content){
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < content.length; i++) {
			stringBuilder.append(content[i]);
		}
		LOGGER.info(stringBuilder.toString());
	}
	public static void error(String content){
		LOGGER.error(content);
	}
	
	
}

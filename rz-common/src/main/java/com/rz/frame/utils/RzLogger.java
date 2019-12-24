package com.rz.frame.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RzLogger {
	private static final Logger LOGGER = LoggerFactory.getLogger(RzLogger.class);
	
	public static void infoObject(Object content) {
		info(content.getClass().getSimpleName(), ":", JsonUtils.serializeObject(content));
	}
	
	public static void info(String content) {
		LOGGER.info(content);
	}
	
	public static void info(String title, String content) {
		content = String.format("%s:%s", getPrefix(title), content);
		LOGGER.info(content);
	}
	
	public static void info(String title, String content, Object... objs) {
		content = String.format("%s:%s", getPrefix(title), content);
		LOGGER.info(content, objs);
	}
	
	public static void info(String content, Object... objs) {
		LOGGER.info(content, objs);
	}
	
	public static void error(String content) {
		LOGGER.error(content);
	}
	
	public static void error(String title, String content) {
		content = String.format("%s:%s", getPrefix(title), content);
		LOGGER.error(content);
	}
	
	public static void error(String title, String content, Object... objs) {
		content = String.format("%s:%s", getPrefix(title), content);
		LOGGER.error(content, objs);
	}
	
	public static void error(String content, Object... objs) {
		LOGGER.error(content, objs);
	}
	
	
	
	private static String getPrefix(String title) {
		return String.format("RZ_%s", title);
	}
	
	public static void main(String[] args) {
		LOGGER.info("测试：{}，参数：{}", "a", "b");
	}
}

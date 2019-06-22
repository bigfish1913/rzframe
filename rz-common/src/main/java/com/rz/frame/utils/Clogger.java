package com.rz.frame.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Clogger {
	
	public Clogger(){

	}
	private static final Logger log = LogManager.getLogger(Clogger.class);
	public static void info(String title, String content) {
		
		log.info(title,content,"");
	 
	}
}

package com.rz.frame.utils;

public class SleepUtils {
	public static void SleepSecond(long seconds) {
		Sleep(seconds*1000);
	}
	
	public static void Sleep(long second) {
		try {
			Thread.sleep(second);
		} catch (InterruptedException ex) {
		
		}
	}
}

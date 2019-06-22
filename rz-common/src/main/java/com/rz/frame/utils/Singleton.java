package com.rz.frame.utils;

public class Singleton {
	private volatile static Singleton singleton;
	
	private Singleton() {
		try {
			Thread.sleep(10);//增加创建对象的耗时
		} catch (Exception e) {
		
		}
	}
	
	public static Singleton getInstance() {
		if (singleton == null) {
			synchronized (Singleton.class) {
				if (singleton == null) {
					singleton = new Singleton();
					return singleton;
				}
			}
		}
		
		return singleton;
	}
	
}

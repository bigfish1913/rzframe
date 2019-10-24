package com.rz.frame.LearnJava.modelDesign.md01.singletons;

public class Singleton01 extends SingletonModel {
	
	private static Singleton01 singleton01;
	//懒汉模式
	//简单，但是线程不安全
	public static Singleton01 getInstance() {
		if (singleton01 == null) {
			singleton01 = new Singleton01();
		}
		return singleton01;
	}
}

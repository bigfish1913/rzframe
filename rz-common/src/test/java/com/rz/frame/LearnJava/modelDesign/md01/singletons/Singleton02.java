package com.rz.frame.LearnJava.modelDesign.md01.singletons;

public class Singleton02 extends SingletonModel {
	
	//饿汉模式
	//但容易产生垃圾，因为一开始就初始化
	private static Singleton02 instance = new Singleton02();
	
	private Singleton02() {
	}
	
	public static Singleton02 getInstance() {
		return instance;
	}
}

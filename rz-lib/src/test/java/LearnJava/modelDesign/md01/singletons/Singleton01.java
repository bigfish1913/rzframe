package LearnJava.modelDesign.md01.singletons;

import LearnJava.modelDesign.md01.singletons.SingletonModel;

public class Singleton01 extends SingletonModel {
 
	private static Singleton01 instance = new Singleton01();
 
	public static Singleton01 getInstance() {
		return instance;
	}
}

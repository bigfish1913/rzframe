package learnJava.modelDesign.md01.singletons;

public class Singleton01 extends SingletonModel {
 
	private static Singleton01 instance = new Singleton01();
 
	public static Singleton01 getInstance() {
		return instance;
	}
}

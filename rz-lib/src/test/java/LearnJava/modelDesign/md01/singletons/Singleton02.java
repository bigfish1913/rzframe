package LearnJava.modelDesign.md01.singletons;

public class Singleton02 extends SingletonModel {
	
	private static Singleton02 instance;
	//懒汉模式
	//简单，但是线程不安全
	public static Singleton02 getInstance() {
		if (instance == null) {
			instance = new Singleton02();
		}
		return instance;
	}
}

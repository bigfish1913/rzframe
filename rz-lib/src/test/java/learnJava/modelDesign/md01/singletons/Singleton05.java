package learnJava.modelDesign.md01.singletons;

public class Singleton05 extends SingletonModel {
	
	private volatile static Singleton05 singleton05;
	//double check 性能最好
	public static Singleton05 getInstance() {
		if (singleton05 == null) {
			synchronized (Singleton05.class) {
				if (singleton05 == null) {
					singleton05 = new Singleton05();
				}
			}
		}
		return singleton05;
	}
}

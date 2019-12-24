package learnJava.modelDesign.md01.singletons;

public class Singleton03  extends  SingletonModel{
	
	private static Singleton03 singleton03;
	
	//加锁，线程安全，但是效率慢
	public synchronized static Singleton03 getInstance() {
		if (singleton03 == null) {
			singleton03 = new Singleton03();
		}
		return singleton03;
	}
}

package LearnJava.modelDesign.md01.singletons;

public class Singleton04 extends SingletonModel {
	
	private static Singleton04 singleton04;
	
	public static Singleton04 getInstance() {
		synchronized (Singleton04.class) {
			if (singleton04 == null) {
				singleton04 = new Singleton04();
			}
		}
		
		return singleton04;
	}
}

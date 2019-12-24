package learnJava.modelDesign.md01.singletons;

public enum Singleton07 {
	INSTANCE;
	/**
	 * 推荐的单例模式之一。但枚举实例在日常开发是很少使用的，就是很简单以导致可读性较差。
	 * 在以上所有的单例模式中，推荐静态内部类单例模式。主要是非常直观，即保证线程安全又保证唯一性。
	 **/
	Singleton07() {
		System.out.println(this.getClass().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//doSomething 该实例支持的行为
	
	//可以省略此方法，通过Singleton.INSTANCE进行操作
	public static Singleton07 getInstance() {
		return Singleton07.INSTANCE;
	}
}

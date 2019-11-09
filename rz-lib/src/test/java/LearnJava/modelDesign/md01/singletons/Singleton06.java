package LearnJava.modelDesign.md01.singletons;

public class Singleton06 extends SingletonModel {
	
	//只有第一次调用getInstance方法时，虚拟机才加载 Inner 并初始化instance ，只有一个线程可以获得对象的初始化锁，其他线程无法进行初始化，保证对象的唯一性。目前此方式是所有单例模式中最推荐的模式
	//但是会产生多余的类
	//反射也可以破解,通过构造函数可以防止破解
	public static Singleton06 getInstance() {
		return Inner.INSTANCE;
	}
	
	static class Inner {
		final  static Singleton06 INSTANCE = new Singleton06();
	}
}


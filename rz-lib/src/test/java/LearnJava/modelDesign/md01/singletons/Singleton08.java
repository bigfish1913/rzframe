package LearnJava.modelDesign.md01.singletons;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public class Singleton08 extends SingletonModel {
	private static final Supplier<Singleton08> InstanceSuppler = Suppliers.memoize(Singleton08::new);
	
	public static Singleton08 getInstance() {
		return InstanceSuppler.get();
	}
}

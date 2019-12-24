package learnJava.rz08;

import java.util.ServiceLoader;

public class AppMain {
	
	public static void main(String[] args) {
		ServiceLoader<IService> loader = ServiceLoader.load(IService.class);
		for (IService service : loader) {
			service.doSomeThing();
		}
	}
}

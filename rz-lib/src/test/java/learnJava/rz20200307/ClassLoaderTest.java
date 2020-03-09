package learnJava.rz20200307;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderTest {
	public static void main(String[] args) {
		MyClassLoader classLoader = new MyClassLoader("MyClassLoader", "D:\\work\\loadClass\\");
		try {
			Class<?> aClass = classLoader.loadClass("learnJava.rz20200307.Demo");
			Object o = aClass.newInstance();
			Method print = aClass.getDeclaredMethod("print");
			print.invoke(o);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		
	}
}

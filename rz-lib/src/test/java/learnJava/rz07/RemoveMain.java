package learnJava.rz07;

import com.rz.frame.utils.RzLogger;

import java.lang.reflect.Field;
import java.util.IdentityHashMap;

public class RemoveMain {
	public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, NoSuchFieldException {
		Runtime.getRuntime().addShutdownHook(new Thread(AppMain::exit01));
		Runtime.getRuntime().addShutdownHook(new Thread(AppMain::exit02));
		Runtime.getRuntime().addShutdownHook(new Thread(AppMain::exit03));
		String className = "java.lang.ApplicationShutdownHooks";
		Class<?> clazz = Class.forName(className);
		Field field = clazz.getDeclaredField("hooks");
		field.setAccessible(true);
			IdentityHashMap<Thread, Thread> map = (IdentityHashMap<Thread, Thread>) field.get(clazz);
			for (Thread thread : map.keySet()) {
				RzLogger.info("found shutdownHook: " + thread.getName());
			}
	}
	
	public static void exit01(){
		System.out.println("exit01");
	}
	public static void exit02(){
		System.out.println("exit02");
	}
	public static void exit03(){
		System.out.println("exit03");
	}
}

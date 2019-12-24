package learnJava.rz03;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapTest {
	
	public static void main(String[] args) {
		ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
		Object put = concurrentHashMap.put("abc", "asdf");
		System.out.println(put);
		put = concurrentHashMap.put("abc", "asdf");
	
		System.out.println(put);
		put = concurrentHashMap.putIfAbsent("abcd", "asdf");
		System.out.println(put);
		put = concurrentHashMap.putIfAbsent("abcd", "asdf1");
		System.out.println(put);
	}
}

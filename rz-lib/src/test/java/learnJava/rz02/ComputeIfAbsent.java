package learnJava.rz02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ComputeIfAbsent {
	public static void main(String[] args) {
		HashMap<String, List<String>> hashMap=new HashMap<>();
		List<String> stringList = Arrays.asList("1", "2");
		hashMap.put("bcd", stringList);
		List<String> abc = hashMap.computeIfAbsent("abc", k -> new ArrayList<>());
		List<String> bcd = hashMap.computeIfAbsent("bcd", k -> new ArrayList<>());
		System.out.println(abc);
		System.out.println(bcd);
		
	}
}

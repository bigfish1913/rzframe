package learnJava.rz02;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;

public class TestMultimap {
	public static void main(String[] args) {
		Multimap<Integer, Integer> map = HashMultimap.<Integer, Integer>create();
		
		map.put(1, 2);
		map.put(1, 2);
		map.put(1, 3);
		map.put(1, 4);
		map.put(2, 3);
		map.put(3, 3);
		map.put(4, 3);
		map.put(5, 3);
		
		System.out.println(map);
		Collection<Integer> integers = map.get(1);
		System.out.println(integers);
 
	}
}

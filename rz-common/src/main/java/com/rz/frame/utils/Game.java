package com.rz.frame.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Game extends Thread {
	static long startFinance = 10000;
	static final List<String> els = new ArrayList<>();
	
	public static void main(String[] args) {
		Game g = new Game();
		g.start();
		Map<String, Long> elementCost = getElementCost();
		try {
			byte[] b = new byte[1];
			while (true) {
				
				System.in.read(b);
				String c = new String(b);
				
				if (c.equals("\n")) {
					Map<String, Long> collect = els.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
					collect.put("剩余：",startFinance);
					System.out.println(collect);
					
					continue;
				}
				if (startFinance < elementCost.get(c)) {
					System.out.println("not enough!");
					continue;
				}
				els.add(c);
				startFinance -= elementCost.get(c);
				SleepUtils.Sleep(100);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
	}
	
	@Override
	public void run() {
		while (true) {
			SleepUtils.Sleep(30);
			for (String e : els) {
				if (e.equals("c")) {
					startFinance += (10 - RandomUtils.getNumberLong(1));
				}
			}
		}
	}
	
	public static Map<String, Long> getElementCost() {
		Map<String, Long> map = new HashMap<>();
		map.put("a", 1000L);
		map.put("t", 10000L);
		map.put("c", 1400L);
		return map;
	}
}

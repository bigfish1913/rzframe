package com.rz.frame.utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class jvmTest {
	
	static class HeapOOM {
	}
	
	public static void main(String[] args) {
		List<HeapOOM> heapOOMList = new ArrayList<>();
		while (true) {
			heapOOMList.add(new HeapOOM());
		}
		
	}
}

package com.rz.frame.LearnJava.rz04;

import java.nio.ByteBuffer;

public class BufferTest {
	public static void main(String[] args) {
		ByteBuffer workingBuffer = ByteBuffer.allocate(22);
		workingBuffer.clear();
		ByteBuffer targetBuffer = ByteBuffer.allocate(100);
		
		workingBuffer.flip();
		workingBuffer.limit(22);
		workingBuffer.putLong(System.currentTimeMillis());
		workingBuffer.putLong(0L);
		workingBuffer.putInt(10);
		workingBuffer.putShort((short) 5);
		targetBuffer.put(workingBuffer.array(), 0, 22);
	}
	
}

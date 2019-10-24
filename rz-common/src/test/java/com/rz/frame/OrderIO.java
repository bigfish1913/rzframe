package com.rz.frame;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class OrderIO {
	public static void main(String[] args) {
		File file = new File("e://dd.iso");
		File file2 = new File("d://01");
		File file3 = new File("e://ee.iso");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		int uniteSize = 1 * 1024 * 1024;
		long maxSize = 1 * 1024 * 1024 * 1024L;
		
		
		file.deleteOnExit();
		ByteBuffer byteBuffer = null;
		try {
			RandomAccessFile randomAccessSourceFile = new RandomAccessFile(file2, "r");
			byteBuffer = ByteBuffer.allocate(uniteSize);
			FileChannel sourceFileChannel = randomAccessSourceFile.getChannel();
			sourceFileChannel.read(byteBuffer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long start = System.currentTimeMillis();
		writeFileByFileChannel(file, byteBuffer, maxSize);
		System.out.println("OrderIO:" + maxSize / 1024  / (System.currentTimeMillis() - start) + "MB/S");
		file.deleteOnExit();
		
		byte[] writeByte = new byte[uniteSize];
		if (file2.canRead()) {
			try {
				FileInputStream fis = new FileInputStream(file2);
				fis.read(writeByte);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		long start1 = System.currentTimeMillis();
		writeFileByStream(file3, writeByte, maxSize);
		System.out.println("Order Random IO:" + maxSize / 1024 / (System.currentTimeMillis() - start1) + "MB/S");
		file3.deleteOnExit();
		//        copyFileByFileChannel(file, file3);
	}
	
	public static void writeFileByStream(File targetFile, byte[] writeByte, long maxSize) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(targetFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		// 使用byte数组读取方式，缓存1MB数据
		
		try {
			while (targetFile.length() < maxSize) {
				fos.write(writeByte);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	public static void writeFileByFileChannel(File targetFile, ByteBuffer byteBuffer, long maxSize) {
		
		
		RandomAccessFile randomAccessTargetFile;
		try {
			
			randomAccessTargetFile = new RandomAccessFile(targetFile, "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		
		FileChannel targetFileChannel = randomAccessTargetFile.getChannel();
		
		// 分配1MB的缓存空间
		
		try {
			while (targetFile.length() < maxSize) {
				byteBuffer.flip();
				targetFileChannel.write(byteBuffer);
				//                System.out.println(targetFile.length());
				//                byteBuffer.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			byteBuffer.clear();
			try {
				targetFileChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void copyFileByFileChannel(File sourceFile, File targetFile) {
		Instant begin = Instant.now();
		
		RandomAccessFile randomAccessSourceFile;
		RandomAccessFile randomAccessTargetFile;
		try {
			// 构造RandomAccessFile，用于获取FileChannel
			randomAccessSourceFile = new RandomAccessFile(sourceFile, "r");
			randomAccessTargetFile = new RandomAccessFile(targetFile, "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		FileChannel sourceFileChannel = randomAccessSourceFile.getChannel();
		FileChannel targetFileChannel = randomAccessTargetFile.getChannel();
		
		// 分配1MB的缓存空间
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
		try {
			while (sourceFileChannel.read(byteBuffer) != -1) {
				byteBuffer.flip();
				targetFileChannel.write(byteBuffer);
				byteBuffer.clear();
				System.out.println(targetFile.length());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				sourceFileChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				targetFileChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("total spent " + Duration.between(begin, Instant.now()).toMillis());
	}
	
	
}

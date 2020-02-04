package learnJava.io.io02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelWrite {
	public static long fileWrite(String filePath, String content, int index) {
		File file = new File(filePath);
		RandomAccessFile randomAccessTargetFile;
		MappedByteBuffer map;
		try {
			randomAccessTargetFile = new RandomAccessFile(file, "rw");
			FileChannel targetFileChannel = randomAccessTargetFile.getChannel();
			map = targetFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, (long) 1024 * 1024 * 1024);
			map.position(index);
			map.put(content.getBytes());
			return map.position();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return 0L;
	}
	
	public static String fileRead(String filePath, long index) {
		File file = new File(filePath);
		RandomAccessFile randomAccessTargetFile;
		MappedByteBuffer map;
		try {
			randomAccessTargetFile = new RandomAccessFile(file, "rw");
			FileChannel targetFileChannel = randomAccessTargetFile.getChannel();
			map = targetFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, index);
			byte[] byteArr = new byte[10 * 1024];
			map.get(byteArr, 0, (int) index);
			return new String(byteArr);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return "";
	}
	
	public static void main(String[] args) {
		String filePath = "D:/fileReader.txt";
		long index = fileWrite(filePath, "123", 0);
		index = fileWrite(filePath, "456", (int)index);
		index = fileWrite(filePath, "456", (int)index);
		index = fileWrite(filePath, "456", (int)index);
		index = fileWrite(filePath, "456", (int)index);
		System.out.println(index);
		String s = fileRead(filePath, index);
		System.out.println(s);
	}
}

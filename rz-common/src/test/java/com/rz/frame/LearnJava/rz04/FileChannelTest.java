package com.rz.frame.LearnJava.rz04;

import com.google.common.base.Stopwatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.concurrent.TimeUnit;

public class FileChannelTest {
	public static void main(String[] args) {
		File file = new File("e://000000000000");
		RandomAccessFile randomAccessTargetFile;
		try {
			randomAccessTargetFile = new RandomAccessFile(file, "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		FileChannel targetFileChannel = randomAccessTargetFile.getChannel();
		try {
			Stopwatch sw = new Stopwatch();
			sw.start();
			for (int i = 0; i < 100; i++) {
				targetFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 2*1024 * 1024 * 1024L-1);
				
			}
			
			
			sw.stop();
			System.out.println(sw.elapsed(TimeUnit.MILLISECONDS));
			
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			file.deleteOnExit();
		}
	}
}

package learnJava.rz04;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelWrite {
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
			
			MappedByteBuffer map = targetFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 2 * 1024*1024);
			int position = map.capacity();
			System.out.println(position);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			file.deleteOnExit();
		}
	}
}

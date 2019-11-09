package LearnJava.rz05;

import com.rz.frame.utils.RandomUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static LearnJava.rz05.ContentHeader.DEFAULT_MAGIC_CODE;

public class OrderIOWriteUtils {
	
	public final long FILE_SIZE = 1024 * 1024 * 10;
	private MappedByteBuffer mappedByteBuffer = null;
	
	public MappedByteBuffer create(String filePath) {
		File file = new File(filePath);
		long fileSize = FILE_SIZE;
		if (file.exists()) {
			fileSize = file.length();
		}
		try {
			RandomAccessFile randomAccessTargetFile = new RandomAccessFile(file, "rw");
			mappedByteBuffer = randomAccessTargetFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
			return mappedByteBuffer;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int appendContent(String content, int starPosition) {
		ContentModel contentModel = new ContentModel(content);
		ByteBuffer slice = mappedByteBuffer.slice();
		slice.position(starPosition);
		if (slice.remaining() < contentModel.getContentHeader().getMsgLength()) {
			return 0;
		}
		slice.putInt(contentModel.getContentHeader().getMagicCode());
		slice.putShort(contentModel.getContentHeader().getVersion());
		slice.putLong(contentModel.getContentHeader().getMsgLength());
		slice.put(contentModel.getContent().getBytes());
		return slice.position();
	}
	
	public int getLastWriteIndex() {
		System.out.println("======getLastWriteIndex=======");
		//		System.out.println(mappedByteBuffer.getInt());
		//		System.out.println(mappedByteBuffer.getShort());
		//		int aLong = (int)mappedByteBuffer.getLong();
		//		System.out.println(aLong);
		//		byte[] byteArr=new byte[aLong];
		//		 mappedByteBuffer.get(byteArr);
		//		System.out.println(new String(byteArr));
		int i = 0;
		while (true) {
			int anInt = mappedByteBuffer.getInt();
			System.out.println(anInt);
			short aShort = mappedByteBuffer.getShort();
			int aLong = (int) mappedByteBuffer.getLong();
			
			if (anInt == DEFAULT_MAGIC_CODE) {
				i++;
				byte[] byteArr = new byte[aLong];
				mappedByteBuffer.get(byteArr);
				System.out.println(new String(byteArr));
				System.out.println("==============================");
				continue;
			}
			
			break;
			
		}
		return i+1;
	}
	
	
	public static void main(String[] args) {
		OrderIOWriteUtils orderIOWriteUtils = new OrderIOWriteUtils();
		orderIOWriteUtils.create("E://000000000000.txt");
		
		int position = orderIOWriteUtils.appendContent(RandomUtils.getRandomString(RandomUtils.getNumberInt(20, 10000)), 0);
		int i = 1;
		while (position < orderIOWriteUtils.FILE_SIZE && position != 0) {
			String randomString = RandomUtils.getRandomString(RandomUtils.getNumberInt(20, 100));
//			System.out.println(randomString);
			position = orderIOWriteUtils.appendContent(randomString, position);
//			System.out.println(position);
			System.out.println(i++);
		}
		
		System.out.println("offset:" + orderIOWriteUtils.getLastWriteIndex());
		System.out.println("OriginOffset:" + i);
		//		Integer i10 = Integer.valueOf("0xdec10ade", 16);
		//		String i2 = Long.toBinaryString(i16);
		//		System.out.println(i16);
		//		System.out.println(i10);
		//		System.out.println(i2);
		
	}
	
}

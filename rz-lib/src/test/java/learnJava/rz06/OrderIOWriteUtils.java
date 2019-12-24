package learnJava.rz06;

import com.google.common.base.Strings;
import learnJava.rz05.ContentModel;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

import static learnJava.rz05.ContentHeader.DEFAULT_MAGIC_CODE;

public class OrderIOWriteUtils {
	
	public final long FILE_SIZE = 1024 * 1024;
	
	private final String filePath;
	private final int baseIndex = 0;
	private AtomicInteger fileOffset = new AtomicInteger(0);
	private File lastFile;
	private MappedByteBuffer mappedByteBuffer;
	
	public String getWriteChar() {
		return writeChar;
	}
	
	public void setWriteChar(String writeChar) {
		this.writeChar = writeChar;
	}
	
	private String writeChar = "A";
	
	public OrderIOWriteUtils(String filePath) {
		this.filePath = filePath;
		lastFile = getLastFile();
		mappedByteBuffer = getMapBuffer(lastFile, 0);
		fileOffset.set(Integer.parseInt(lastFile.getName()));
	}
	
	private File getLastFile() {
		File file = new File(filePath);
		File[] files = file.listFiles();
		if (files == null || files.length == 0) {
			//没有文件
			String fileName = StoreUtils.offset2FileName(fileOffset.get());
			fileOffset.incrementAndGet();
			return new File(filePath, fileName);
		}
		return Arrays.stream(files).max(Comparator.comparing(File::getName)).get();
		
	}
	
	
	private MappedByteBuffer getMapBuffer(File file, int startPosition) {
		long fileSize = FILE_SIZE;
		if (file.exists()) {
			fileSize = file.length();
		}
		try {
			RandomAccessFile randomAccessTargetFile = new RandomAccessFile(file, "rw");
			return randomAccessTargetFile.getChannel().map(FileChannel.MapMode.READ_WRITE, baseIndex + startPosition, fileSize);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void allocateFile() {
		String s = StoreUtils.offset2FileName(fileOffset.getAndIncrement());
		lastFile = new File(filePath + "\\" + s);
		mappedByteBuffer = getMapBuffer(lastFile, 0);
	}
	
	private int appendContent(String content, int starPosition) {
		ContentModel contentModel = new ContentModel(content);

		if (mappedByteBuffer.remaining() < contentModel.getContentHeader().getMsgLength()) {
			allocateFile();
			writeChar = String.valueOf(writeChar.toCharArray()[0] += 1);
			return 0;
		}
		mappedByteBuffer.putInt(contentModel.getContentHeader().getMagicCode());
		mappedByteBuffer.putShort(contentModel.getContentHeader().getVersion());
		mappedByteBuffer.putLong(contentModel.getContentHeader().getMsgLength());
		mappedByteBuffer.put(contentModel.getContent().getBytes());
		return mappedByteBuffer.position();
	}
	
	private int getLastWriteIndex() {
		int offset = baseIndex;
		while (true) {
			int anInt = mappedByteBuffer.getInt();
			short aShort = mappedByteBuffer.getShort();
			int msgLong = (int) mappedByteBuffer.getLong();
			
			if (anInt == DEFAULT_MAGIC_CODE) {
				byte[] byteArr = new byte[msgLong];
				mappedByteBuffer.get(byteArr);
				offset += (4 + 2 + msgLong);
				continue;
			}
			break;
		}
		return offset;
	}
	
	
	public static void main(String[] args) {
		OrderIOWriteUtils orderIOWriteUtils = new OrderIOWriteUtils("E:\\test");
		
		int lastWriteIndex = orderIOWriteUtils.getLastWriteIndex();
		
		while (true) {
			
			lastWriteIndex = orderIOWriteUtils.appendContent(Strings.repeat(orderIOWriteUtils.getWriteChar(), 1024 * 100), lastWriteIndex);
			try {
				orderIOWriteUtils.setWriteChar(String.valueOf(orderIOWriteUtils.getWriteChar().toCharArray()[0] += 1));
				if (orderIOWriteUtils.getWriteChar().equals("Z")) {
					orderIOWriteUtils.setWriteChar("A");
				}
				
				Thread.sleep(100);
				System.out.println(lastWriteIndex);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		//		System.out.println("offset:" + orderIOWriteUtils.getLastWriteIndex());
		//System.out.println("OriginOffset:" + i);
		//		Integer i10 = Integer.valueOf("0xdec10ade", 16);
		//		String i2 = Long.toBinaryString(i16);
		//		System.out.println(i16);
		//		System.out.println(i10);
		//		System.out.println(i2);
		
	}
	
}

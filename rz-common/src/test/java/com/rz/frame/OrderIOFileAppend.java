package com.rz.frame;

import io.netty.util.internal.StringUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.NumberFormat;

public class OrderIOFileAppend {
	
	private int writeIndex;
	private String filePath;
	//10MB
	private int fileSize;
	private int uniteSize;
	private ByteBuffer map;
	private int index;
	
	public OrderIOFileAppend() {
		index = 0;
		this.filePath = "D:\\file\\" + offset2FileName(index);
		fileSize = 1024 * 1024 * 1024;
		uniteSize = 1024;
		writeIndex = 0;
		
	}
	
	public void getNextSegments() {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			FileChannel channel = randomAccessFile.getChannel();
			map = channel.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	String offset2FileName(final long offset) {
		final NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumIntegerDigits(20);
		nf.setMaximumFractionDigits(0);
		nf.setGroupingUsed(false);
		return nf.format(offset);
	}
	
	public void writeContent(ByteBuffer content) throws IOException {
		if (StringUtil.isNullOrEmpty(filePath)) {
			throw new FileNotFoundException("文件路径不合法：" + filePath);
		}
		if (map == null) {
			getNextSegments();
		}
		//换下一个文件片段
		if (writeIndex >= fileSize - 100) {
			index++;
			this.filePath = "D:\\file\\" + offset2FileName(index);
			writeIndex = 0;
			getNextSegments();
		}
		try {
			ByteBuffer slice = map.slice();
			slice.position(writeIndex);
			content.flip();
			slice.put(content);
			writeIndex += 11;
		} finally {
		
		}
	}
	
	public static void main(String[] args) {
		OrderIOFileAppend append = new OrderIOFileAppend();
		ByteBuffer byteBuffer = ByteBuffer.allocate(append.uniteSize);
		for (int i = 0; i < 1024 * 1024 * 1024 / 4; i++) {
			try {
				byteBuffer.clear();
				String str = (i + "--");
				byteBuffer.put(str.getBytes());
				append.writeContent(byteBuffer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}



package learnJava.rz20200309.ParseClass;

import com.rz.frame.utils.StringUtils;

import java.io.*;

public class ClassComplieFile {
	private String filePath;
	private String classHexCnt;
	private int next = 0;
	private int classLength = 0;
	
	public ClassComplieFile(String path) {
		if (!path.contains(".class")) {
			path += ".class";
		}
		this.filePath = path;
		
		byte[] bytes = readClassFileToByteArray();
		classHexCnt = byteToHex(bytes);
		this.classLength = classHexCnt.length();
	}
	
	public String getHexNext(int length) {
		try {
			length = length * 2;
			String classContent = getClassContent(next, next + length);
			next += length;
			return classContent;
		} catch (Exception ex) {
			return "";
		}
	}
	
	public Integer getIntNext(int length) {
		String next = getHexNext(length);
		return Integer.parseInt(next, 16);
	}
	
	public String getNext(int length) {
		String next = getHexNext(length);
		return String.valueOf(Integer.parseInt(next, 16));
	}
	
	public boolean hasNext() {
		return next < classLength;
	}
	
	public void reset() {
		next = 0;
	}
	
	public String getClassContent(int start, int end) throws Exception {
		if (StringUtils.isEmpty(classHexCnt)) {
			throw new Exception("尚未被初始化");
		}
		if (start > end) {
			throw new Exception("参数不合法");
		}
		if (start > classHexCnt.length()) {
			throw new Exception("起始位置超过了");
		}
		if (end > classHexCnt.length()) {
			end = classHexCnt.length();
		}
		return classHexCnt.substring(start, end);
		
		
	}
	
	private String byteToHex(byte[] bytes) {
		String strHex = "";
		StringBuilder sb = new StringBuilder("");
		for (int n = 0; n < bytes.length; n++) {
			strHex = Integer.toHexString(bytes[n] & 0xFF);
			sb.append((strHex.length() == 1) ? "0" + strHex : strHex); // 每个字节由两个字符表示，位数不够，高位补0
		}
		return sb.toString().trim();
	}
	
	private byte[] readClassFileToByteArray() {
		InputStream is = null;
		byte[] returnData = null;
		File file = new File(filePath);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			is = new FileInputStream(file);
			int tmp = 0;
			while ((tmp = is.read()) != -1) {
				os.write(tmp);
			}
			returnData = os.toByteArray();
			return returnData;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnData;
	}
	
}

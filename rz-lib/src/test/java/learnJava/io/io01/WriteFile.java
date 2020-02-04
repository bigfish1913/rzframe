package learnJava.io.io01;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFile {
	public static void main(String[] args) {
		
		writeFile("abcadfas", "D:/text4.txt");
	}
	
	public static void writeFile(String content, String filePath) {
		FileOutputStream outputStream = null;
		try {
			File file = new File(filePath);
			boolean isCreate = file.createNewFile();//创建文件
			if (isCreate) {
				outputStream = new FileOutputStream(file);//形参里面可追加true参数，表示在原有文件末尾追加信息
				outputStream.write(content.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}

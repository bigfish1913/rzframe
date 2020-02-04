package learnJava.io.io02;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileStreamHelper {
	
	public static void fileWrite(String filePath, String content) {
		FileOutputStream outputStream = null;
		try {
			File file = new File(filePath);
			boolean isCreate = file.createNewFile();//创建文件
			if (isCreate) {
				outputStream = new FileOutputStream(file);//形参里面可追加true参数，表示在原有文件末尾追加信息
				outputStream.write(content.getBytes());
			}else {
				outputStream = new FileOutputStream(file,true);//表示在原有文件末尾追加信息
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
	
	public static void fileRead(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			try {
				//创建FileInputStream对象，读取文件内容
				FileInputStream fis = new FileInputStream(file);
				byte[] bys = new byte[1024];
				while (fis.read(bys, 0, bys.length) != -1) {
					//将字节数组转换为字符串
					System.out.print(new String(bys, StandardCharsets.UTF_8));
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		String filePath = "D:/FileInputStream.txt";
		fileWrite(filePath, "乾 卦， 上下 卦 皆 由“ 乾” 组成， 卦 形 作“”， 乾 从 形 上 讲 象征“ 天”， 所以 称 此 卦 为重 天 乾 卦。 为什么 说 乾 从 形 上 讲 象征“ 天” 呢？ 古人 认为 天 是 阳气 积聚 而成， 有 纯 阳 之 性。 清代 李 士 珍 在《 周易 注》 中 说“ 天 者， 阳 之 宗， 物 莫 先 焉， 物 莫大 焉， 故 画 一 以 象之”， 又以“ 易 究 天地 之 原， 立 三 才 之道”， 所以“ 三 画 以 成 一 卦”。 文史哲. 易经(白话全译) (时光文库) (p. 1). 立信会计出版社. Kindle 版本. ");
		fileRead(filePath);
	}
	
	
}

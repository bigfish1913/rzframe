package learnJava.io.io02;

import java.io.*;

public class BuffredRWHelper {
	public static void fileWrite(String filePath, String content) {
		File file = new File(filePath);
		//创建FileWriter对象
		BufferedWriter writer = null;
		try {
			//如果文件不存在，创建文件
			if (!file.exists())
				file.createNewFile();
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);//写入内容
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void fileRead(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			try {
				//创建FileReader对象，读取文件中的内容
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.print(line);
				}
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		String filePath = "D:/fileReader.txt";
		fileWrite(filePath,"乾 卦， 上下 卦 皆 由“ 乾” 组成， 卦 形 作“”， 乾 从 形 上 讲 象征“ 天”， 所以 称 此 卦 为重 天 乾 卦。 为什么 说 乾 从 形 上 讲 象征“ 天” 呢？ 古人 认为 天 是 阳气 积聚 而成， 有 纯 阳 之 性。 清代 李 士 珍 在《 周易 注》 中 说“ 天 者， 阳 之 宗， 物 莫 先 焉， 物 莫大 焉， 故 画 一 以 象之”， 又以“ 易 究 天地 之 原， 立 三 才 之道”， 所以“ 三 画 以 成 一 卦”。 文史哲. 易经(白话全译) (时光文库) (p. 1). 立信会计出版社. Kindle 版本. ");
		fileRead(filePath);
	}
}

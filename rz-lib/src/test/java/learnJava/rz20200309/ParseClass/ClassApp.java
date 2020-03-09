package learnJava.rz20200309.ParseClass;

import com.rz.frame.utils.JsonUtils;

public class ClassApp {
	public static void main(String[] args) throws Exception {
		ClassComplieFile classFile = new ClassComplieFile("D:\\work\\loadClass\\learnJava\\rz20200307\\Demo");
	 
		
		while (classFile.hasNext()) {
			System.out.printf(classFile.getHexNext(2));
			System.out.printf(" ");
		}
		System.out.println(" ");
		classFile.reset();
		try {
			ClassParseInfo classParseInfo=new ClassParseInfo();
			MagicCodeInfo magicCodeInfo = new MagicCodeInfo();
			magicCodeInfo.process(classFile);
			VersionInfo versionInfo = new VersionInfo();
			versionInfo.process(classFile);
			
			classParseInfo.setMagicCodeInfo(magicCodeInfo);
			classParseInfo.setVersionInfo(versionInfo);
			
			System.out.println(JsonUtils.serializeObject(classParseInfo));
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
	}
}

package learnJava.rz20200307;

import java.io.*;

public class MyClassLoader extends ClassLoader {
	
	private String name;
	private String path;
	
	@Override
	public String toString() {
		return "MyClassLoader{" + "name='" + name + '\'' + ", path='" + path + '\'' + '}';
	}
	
	public MyClassLoader(String name, String path) {
		this.name = name;
		this.path = path;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] bytes = readClassFileToByteArray(name);
		return this.defineClass(name, bytes, 0, bytes.length);
	}
	
	private byte[] readClassFileToByteArray(String name) {
		InputStream is = null;
		byte[] returnData = null;
		
		name = name.replace(".", "/");
		String filePath = path + name + ".class";
		
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

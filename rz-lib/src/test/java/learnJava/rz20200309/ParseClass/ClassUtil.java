package learnJava.rz20200309.ParseClass;

import learnJava.rz20200307.ClassMainData;

import java.util.Set;

public class ClassUtil {
	public static String getAccFlag(int accFlag) {
		Set<Integer> keyList = ClassMainData.AccFlag_map.keySet();
		StringBuilder accStr = new StringBuilder();
		for (Integer key : keyList) {
			if ((accFlag & key) == key) {//说明满足
				accStr.append(ClassMainData.AccFlag_map.get(key)).append(",");
			}
		}
		
		return accStr.toString();
	}
	
	public static String getJvmCmd(String code) {
		String[] codeArr = code.split("");
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < codeArr.length; i += 2) {
			String key = "0x" + codeArr[i] + codeArr[i + 1];
			String cmd = ClassMainData.Jvm_map.get(key);
			stringBuilder.append(cmd);
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
	
	public static String hexStringToString(String s) {
		if (s == null || s.equals("")) {
			return null;
		}
		s = s.replace(" ", "");
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "UTF-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}
	
}

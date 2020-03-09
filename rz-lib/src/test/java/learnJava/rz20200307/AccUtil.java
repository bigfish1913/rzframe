package learnJava.rz20200307;

import java.util.Set;

public class AccUtil {
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
	
}

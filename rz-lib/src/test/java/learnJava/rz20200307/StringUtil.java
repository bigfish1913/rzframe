package learnJava.rz20200307;

public class StringUtil {
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
			new String();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}
}

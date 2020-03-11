package learnJava.rz20200309.ParseClass.Constant;

import learnJava.rz20200309.ParseClass.ClassComplieFile;
import learnJava.rz20200309.ParseClass.ClassUtil;

public class CONSTANT_Utf8_info  extends Constant{
	private  short tag;
	private  int length;
	private  String hexStr;
	private  String text;
	
	public CONSTANT_Utf8_info(int tag,ClassComplieFile classFile) {
		 this.tag=(short) tag;
		this.length = classFile.getIntNext(2);
		this.hexStr = classFile.getHexNext(length);
		this.text = ClassUtil.hexStringToString(hexStr);
	}
	
	public short getTag() {
		return tag;
	}
	
	public void setTag(short tag) {
		this.tag = tag;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public String getHexStr() {
		return hexStr;
	}
	
	public void setHexStr(String hexStr) {
		this.hexStr = hexStr;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}

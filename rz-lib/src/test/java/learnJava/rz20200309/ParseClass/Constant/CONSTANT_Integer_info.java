package learnJava.rz20200309.ParseClass.Constant;

import learnJava.rz20200309.ParseClass.ClassComplieFile;

public class CONSTANT_Integer_info {
	private short tag;
	private int bytes;
	
	public CONSTANT_Integer_info(int tag, ClassComplieFile classComplieFile) {
		this.tag = (short)tag;
		this.bytes = classComplieFile.getIntNext(2);
	}
	
	public short getTag() {
		return tag;
	}
	
	public void setTag(short tag) {
		this.tag = tag;
	}
	
	public int getBytes() {
		return bytes;
	}
	
	public void setBytes(int bytes) {
		this.bytes = bytes;
	}
}

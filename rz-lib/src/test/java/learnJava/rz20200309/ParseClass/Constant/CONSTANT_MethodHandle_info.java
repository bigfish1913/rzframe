package learnJava.rz20200309.ParseClass.Constant;

import learnJava.rz20200309.ParseClass.ClassComplieFile;

public class CONSTANT_MethodHandle_info {
	private short tag;
	private int reference_kind;
	private int reference_index;
	
	public CONSTANT_MethodHandle_info(ClassComplieFile classFile) {
		this.reference_kind = classFile.getIntNext(2);
		this.reference_index = classFile.getIntNext(2);
		
	}
	
	public short getTag() {
		return tag;
	}
	
	public void setTag(short tag) {
		this.tag = tag;
	}
	
	public int getReference_kind() {
		return reference_kind;
	}
	
	public void setReference_kind(int reference_kind) {
		this.reference_kind = reference_kind;
	}
	
	public int getReference_index() {
		return reference_index;
	}
	
	public void setReference_index(int reference_index) {
		this.reference_index = reference_index;
	}
}

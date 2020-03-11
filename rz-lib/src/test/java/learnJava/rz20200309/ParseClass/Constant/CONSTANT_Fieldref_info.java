package learnJava.rz20200309.ParseClass.Constant;

import learnJava.rz20200309.ParseClass.ClassComplieFile;

public class CONSTANT_Fieldref_info extends  Constant {
	private short tag;
	private int class_index;
	private int name_and_type_index;
	
	public CONSTANT_Fieldref_info(int tag, ClassComplieFile classFile) {
		this.tag = (short)tag;
		this.class_index = classFile.getIntNext(2);
		this.name_and_type_index =  classFile.getIntNext(2);
	}
	
	public short getTag() {
		return tag;
	}
	
	public void setTag(short tag) {
		this.tag = tag;
	}
	
	public int getClass_index() {
		return class_index;
	}
	
	public void setClass_index(int class_index) {
		this.class_index = class_index;
	}
	
	public int getName_and_type_index() {
		return name_and_type_index;
	}
	
	public void setName_and_type_index(int name_and_type_index) {
		this.name_and_type_index = name_and_type_index;
	}
}

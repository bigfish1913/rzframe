package learnJava.rz20200309.ParseClass.Constant;

import learnJava.rz20200309.ParseClass.ClassComplieFile;

public class CONSTANT_Methodref_info extends  Constant{
	private short tag;
	private int class_index;
	private int name_and_type_index;
	
	public CONSTANT_Methodref_info(int tag,ClassComplieFile classFile) {
		this.tag=(short) tag;
		this.class_index = classFile.getIntNext(2);
		this.name_and_type_index = classFile.getIntNext(2);
		
	}
}

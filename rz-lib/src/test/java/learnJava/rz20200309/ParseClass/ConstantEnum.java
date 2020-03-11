package learnJava.rz20200309.ParseClass;

public enum ConstantEnum {
	CONSTANT_Utf8_info(1 ),
	CONSTANT_Integer_info(3),
	CONSTATN_Float_info(4 ),
	CONSTANT_Long_info(5 ),
	CONSTANT_Double_info(6 ),
	CONSTANT_Class_info(7 ),
	CONSTANT_String_info(8 ),
	CONSTANT_Fieldref_info(9 ),
	CONSTANT_Methodref_info(10),
	CONSTANT_InterfaceMethodref_info(11),
	CONSTANT_NameAndType_info(12),
	CONSTANT_MethodHandler_info(15),
	CONSTANT_MethodType_info(16),
	CONSTANT_Dynamic_info(17),
	CONSTANT_InvokeDynamic_info(18),
	CONSTANT_Module_info(19),
	CONSTANT_Package_info(20);
	private int code;
	  ConstantEnum(int code){
		this.code=code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static ConstantEnum getMessageType(int code) {
		for (ConstantEnum mt : ConstantEnum.values()) {
			if (mt.getCode() == code) {
				return mt;
			}
			
		}
		return null;
	}
	
}

package learnJava.rz20200309.ParseClass;

public enum ClassEnum {
	Magic_Code(8,"Magic_Code"),
	JDK_Version(2,"JDK_Version"),
	JDK_Main_Version(2,"JDK_Main_Version"),
	Constant_Count(2,"Constant_Count"),
	Access_Flag(1,"Access_Flag");
	
	private int code;
	private String name;
	
	ClassEnum(int value, String name) {
		this.code = value;
		this.name = name;
	}
	public int getCode() {
		return this.code;
	}
	public String getName() {
		return this.name;
	}
}

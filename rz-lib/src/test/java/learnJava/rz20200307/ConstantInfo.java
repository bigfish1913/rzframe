package learnJava.rz20200307;

public class ConstantInfo {
	private String hex;
	
	private Integer value;
	private String args1;
	private Integer value1;
	private String args2;
	private Integer value2;
	String type;
	String text;
	
	public ConstantInfo(String hex, String type, String args1) {
		this.hex = hex;
		value=Integer.parseInt(hex,16);
		this.args1 = args1;
		value1=Integer.parseInt(args1,16);
		
		this.type = type.replace("CONSTANT_","").replace("_info","");
		
		
		
	}
	
	public String getArgs2() {
		return args2;
	}
	
	public void setArgs2(String args2) {
		this.args2 = args2;
		
		if(hex.equals("01")){
			text=StringUtil.hexStringToString(args2);
		}else {
			value2=Integer.parseInt(args2,16);
		}
	}
	
	public String getArgs1() {
		return args1;
	}
	
	public String getHex() {
		return hex;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public Integer getValue1() {
		return value1;
	}
	
	public Integer getValue2() {
		return value2;
	}
	
	public String getType() {
		return type;
	}
	
	public String getText() {
		return text;
	}
	
	@Override
	public String toString() {
		return "ConstantInfo{" + "hex='" + hex + '\'' + ", value=" + value + ", args1='" + args1 + '\'' + ", value1=" + value1 + ", args2='" + args2 + '\'' + ", value2=" + value2 + ", type='" + type + '\'' + ", text='" + text + '\'' + '}';
	}
}

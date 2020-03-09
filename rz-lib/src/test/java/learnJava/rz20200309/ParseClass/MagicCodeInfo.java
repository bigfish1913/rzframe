package learnJava.rz20200309.ParseClass;

public class MagicCodeInfo extends BaseCode {
	private String magicCode;
	
	public String getMagicCode() {
		return magicCode;
	}
	
	public void setMagicCode(String magicCode) {
		this.magicCode = magicCode;
	}
	
	@Override
	public void register() {
	
	}
	
	public void process(ClassComplieFile classFile) {
		this.setMagicCode(classFile.getHexNext(4));
	}
}

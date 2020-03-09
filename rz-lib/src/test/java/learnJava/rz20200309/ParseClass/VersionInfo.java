package learnJava.rz20200309.ParseClass;


import learnJava.rz20200307.ClassMainData;

public class VersionInfo extends BaseCode {
	
	private String jdkVerion;
	private String jdkMainVersion;
	
	@Override
	public void register() {
	
	}
	
	@Override
	public void process(ClassComplieFile classFile) {
		setJdkVerion(classFile.getNext(ClassEnum.JDK_Version.getCode()));
		String jdkVersionKey = classFile.getNext(ClassEnum.JDK_Main_Version.getCode());
		setJdkMainVersion(ClassMainData.JdkVersion.get(jdkVersionKey));
	}
	
	public String getJdkVerion() {
		return jdkVerion;
	}
	
	public void setJdkVerion(String jdkVerion) {
		this.jdkVerion = jdkVerion;
	}
	
	public String getJdkMainVersion() {
		return jdkMainVersion;
	}
	
	public void setJdkMainVersion(String jdkMainVersion) {
		this.jdkMainVersion = jdkMainVersion;
	}
}

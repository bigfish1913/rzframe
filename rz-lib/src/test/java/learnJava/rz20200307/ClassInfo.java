package learnJava.rz20200307;

import java.util.List;

public class ClassInfo {
	String magicCode;
	String jdkVersion;
	String jdkMainVersion;
	String constanstCount;
	String constantContent;
	String accessFlag;
	String classIndex;
	String parentIndex;
	String interFaceIndex;
	int filedCount;
	List<FiledInfo> filedInfoList;
	List<FiledInfo> methodList;
	int methodCount;
	CodeInfo codeInfo;
	
	
	public CodeInfo getCodeInfo() {
		return codeInfo;
	}
	
	public void setCodeInfo(CodeInfo codeInfo) {
		this.codeInfo = codeInfo;
	}
	
	public List<FiledInfo> getMethodList() {
		return methodList;
	}
	
	public void setMethodList(List<FiledInfo> methodList) {
		this.methodList = methodList;
	}
	
	public int getMethodCount() {
		return methodCount;
	}
	
	public void setMethodCount(int methodCount) {
		this.methodCount = methodCount;
	}
	
	public List<FiledInfo> getFiledInfoList() {
		return filedInfoList;
	}
	
	public void setFiledInfoList(List<FiledInfo> filedInfoList) {
		this.filedInfoList = filedInfoList;
	}
	
	public String getMagicCode() {
		return magicCode;
	}
	
	public void setMagicCode(String magicCode) {
		this.magicCode = magicCode;
	}
	
	public String getJdkVersion() {
		return jdkVersion;
	}
	
	public void setJdkVersion(String jdkVersion) {
		this.jdkVersion = jdkVersion;
	}
	
	public String getJdkMainVersion() {
		return jdkMainVersion;
	}
	
	public void setJdkMainVersion(String jdkMainVersion) {
		this.jdkMainVersion = jdkMainVersion;
	}
	
	public String getConstanstCount() {
		return constanstCount;
	}
	
	public void setConstanstCount(String constanstCount) {
		this.constanstCount = constanstCount;
	}
	
	public String getConstantContent() {
		return constantContent;
	}
	
	public void setConstantContent(String constantContent) {
		this.constantContent = constantContent;
	}
	
	public String getAccessFlag() {
		return accessFlag;
	}
	
	public void setAccessFlag(String accessFlag) {
		this.accessFlag = accessFlag;
	}
	
	public String getClassIndex() {
		return classIndex;
	}
	
	public void setClassIndex(String classIndex) {
		this.classIndex = classIndex;
	}
	
	public String getParentIndex() {
		return parentIndex;
	}
	
	public void setParentIndex(String parentIndex) {
		this.parentIndex = parentIndex;
	}
	
	public String getInterFaceIndex() {
		return interFaceIndex;
	}
	
	public void setInterFaceIndex(String interFaceIndex) {
		this.interFaceIndex = interFaceIndex;
	}
	
	public int getFiledCount() {
		return filedCount;
	}
	
	public void setFiledCount(int filedCount) {
		this.filedCount = filedCount;
	}
	
	@Override
	public String toString() {
		return "ClassInfo{" + "magicCode='" + magicCode + '\'' + ", jdkVersion='" + jdkVersion + '\'' + ", jdkMainVersion='" + jdkMainVersion + '\'' + ", constanstCount='" + constanstCount + '\'' + ", constantContent='" + constantContent + '\'' + ", accessFlag='" + accessFlag + '\'' + ", classIndex='" + classIndex + '\'' + ", parentIndex='" + parentIndex + '\'' + ", interFaceIndex='" + interFaceIndex + '\'' + ", filedCount=" + filedCount + ", filedInfoList=" + filedInfoList + ", methodList=" + methodList + ", methodCount=" + methodCount + '}';
	}
}

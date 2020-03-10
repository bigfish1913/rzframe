package learnJava.rz20200309.ParseClass;

import learnJava.rz20200307.CodeInfo;

import java.util.HashMap;
import java.util.List;

public class ClassParseInfo {
	
	private MagicCodeInfo magicCodeInfo;
	private VersionInfo versionInfo;
	private HashMap<String, List<String>> constantInfo;
	private String accessFlag;
	private String classIndex;
	private String parentIndex;
	private String interFaceIndex;
	private int filedCount;
	
	private List<FieldInfo> filedInfoList;
	private List<FieldInfo> methodList;
	private int methodCount;
	private CodeInfo codeInfo;
	
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
	
	public MagicCodeInfo getMagicCodeInfo() {
		return magicCodeInfo;
	}
	
	public void setMagicCodeInfo(MagicCodeInfo magicCodeInfo) {
		this.magicCodeInfo = magicCodeInfo;
	}
	
	public VersionInfo getVersionInfo() {
		return versionInfo;
	}
	
	public void setVersionInfo(VersionInfo versionInfo) {
		this.versionInfo = versionInfo;
	}
	
	public HashMap<String, List<String>> getConstantInfo() {
		return constantInfo;
	}
	
	public void setConstantInfo(HashMap<String, List<String>> constantInfo) {
		this.constantInfo = constantInfo;
	}
	
	public int getFiledCount() {
		return filedCount;
	}
	
	public void setFiledCount(int filedCount) {
		this.filedCount = filedCount;
	}
	
	public List<FieldInfo> getFiledInfoList() {
		return filedInfoList;
	}
	
	public void setFiledInfoList(List<FieldInfo> filedInfoList) {
		this.filedInfoList = filedInfoList;
	}
	
	public List<FieldInfo> getMethodList() {
		return methodList;
	}
	
	public void setMethodList(List<FieldInfo> methodList) {
		this.methodList = methodList;
	}
	
	public int getMethodCount() {
		return methodCount;
	}
	
	public void setMethodCount(int methodCount) {
		this.methodCount = methodCount;
	}
	
	public CodeInfo getCodeInfo() {
		return codeInfo;
	}
	
	public void setCodeInfo(CodeInfo codeInfo) {
		this.codeInfo = codeInfo;
	}
}

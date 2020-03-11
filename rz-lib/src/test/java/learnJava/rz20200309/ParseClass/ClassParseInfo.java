package learnJava.rz20200309.ParseClass;

import learnJava.rz20200307.CodeInfo;
import learnJava.rz20200309.ParseClass.Constant.CONSTANT_Utf8_info;
import learnJava.rz20200309.ParseClass.Constant.Wrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassParseInfo {
	
	private String magicCode;
	private String versionInfo;
	private String jdkVersionInfo;
	private int constantCount;
	private HashMap<Integer, List<Wrapper<?>>> constantMap;
	private HashMap<Integer, List<Wrapper<CONSTANT_Utf8_info>>> utf8Map;
	private String accessFlag;
	private String classIndex;
	private String parentIndex;
	private String interFaceIndex;
	private int filedCount;
	
	private List<FieldInfo> filedInfoList;
	private List<FieldInfo> methodList;
	private int methodCount;
	private CodeInfo codeInfo;
	
	public ClassParseInfo() {
		this.constantMap = new HashMap<>();
		this.utf8Map = new HashMap<>();
	}
	
	public String getMagicCode() {
		return magicCode;
	}
	
	public void setMagicCode(String magicCode) {
		this.magicCode = magicCode;
	}
	
	public String getVersionInfo() {
		return versionInfo;
	}
	
	public void setVersionInfo(String versionInfo) {
		this.versionInfo = versionInfo;
	}
	
	public String getJdkVersionInfo() {
		return jdkVersionInfo;
	}
	
	public void setJdkVersionInfo(String jdkVersionInfo) {
		this.jdkVersionInfo = jdkVersionInfo;
	}
	
	public int getConstantCount() {
		return constantCount;
	}
	
	public void setConstantCount(int constantCount) {
		this.constantCount = constantCount;
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
	
	public <T> void pushConstant(int index, int type, T methodref) {
		Wrapper<T> wrapper = new Wrapper<T>(index, type, methodref);
		List<Wrapper<?>> wrappers = this.constantMap.computeIfAbsent(index, k -> new ArrayList<>());
		wrappers.add(wrapper);
		if (type == 1) {
			List<Wrapper<CONSTANT_Utf8_info>> utf8List = this.utf8Map.computeIfAbsent(index, k -> new ArrayList<>());
			CONSTANT_Utf8_info utf8Instance = (CONSTANT_Utf8_info) methodref;
			Wrapper<CONSTANT_Utf8_info> utf8 = new Wrapper<>(index, type, utf8Instance);
			utf8List.add(utf8);
		}
		
		
	}
	
	
}

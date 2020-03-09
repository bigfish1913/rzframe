package learnJava.rz20200307;

public class FiledInfo {
	
	private String accFlag;
	private String nameIndex;
	private String decrInfo;
	private int attributteCount;
	private String attrInfo;
	
	public String getAccFlag() {
		return accFlag;
	}
	
	public void setAccFlag(String accFlag) {
		this.accFlag = accFlag;
	}
	
	public String getNameIndex() {
		return nameIndex;
	}
	
	public void setNameIndex(String nameIndex) {
		this.nameIndex = nameIndex;
	}
	
	public String getDecrInfo() {
		return decrInfo;
	}
	
	public void setDecrInfo(String decrInfo) {
		this.decrInfo = decrInfo;
	}
	
	public int getAttributteCount() {
		return attributteCount;
	}
	
	public void setAttributteCount(int attributteCount) {
		this.attributteCount = attributteCount;
	}
	
	public String getAttrInfo() {
		return attrInfo;
	}
	
	public void setAttrInfo(String attrInfo) {
		this.attrInfo = attrInfo;
	}
	
	@Override
	public String toString() {
		return "FiledInfo{" + "accFlag='" + accFlag + '\'' + ", nameIndex='" + nameIndex + '\'' + ", decrInfo='" + decrInfo + '\'' + ", attributteCount='" + attributteCount + '\'' + ", attrInfo='" + attrInfo + '\'' + '}';
	}
}

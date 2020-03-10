package learnJava.rz20200309.ParseClass;

public class FieldInfo {
	private String access_flags;
	private int name_index;
	private int descriptor_index;
	private int attributes_count;
	private String attributeInfo;
	
	public String getAccess_flags() {
		return access_flags;
	}
	
	public void setAccess_flags(String access_flags) {
		this.access_flags = access_flags;
	}
	
	public int getName_index() {
		return name_index;
	}
	
	public void setName_index(int name_index) {
		this.name_index = name_index;
	}
	
	public int getDescriptor_index() {
		return descriptor_index;
	}
	
	public void setDescriptor_index(int descriptor_index) {
		this.descriptor_index = descriptor_index;
	}
	
	public int getAttributes_count() {
		return attributes_count;
	}
	
	public void setAttributes_count(int attributes_count) {
		this.attributes_count = attributes_count;
	}
	
	public String getAttributeInfo() {
		return attributeInfo;
	}
	
	public void setAttributeInfo(String attributeInfo) {
		this.attributeInfo = attributeInfo;
	}
}

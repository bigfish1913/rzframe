package com.rz.frame.netty;

public enum ContentType {
	Default((byte) 0),
	File((byte) 1),
	Other((byte) 2);
	private byte code;
	ContentType(byte code) {
		this.code = code;
	}
	
	public byte getValue() {
		return code;
	}
	
	public static ContentType getContentType(String typeName){
		for (ContentType mt :ContentType.values()) {
			if(mt.toString().equals(typeName.trim())){
				return mt;
			}
			
		}
		return null;
	}
}

package com.rz.frame.netty;

public enum ContentType {
	Default((byte) 0),
	File((byte) 1),
	Other((byte) 2);
	private int code;
	ContentType(int code) {
		this.code = code;
	}
	
	public int getValue() {
		return code;
	}
	
	public static ContentType getMessageType(String typeName){
		for (ContentType mt :ContentType.values()) {
			if(mt.toString().equals(typeName.trim())){
				return mt;
			}
			
		}
		return null;
	}
}

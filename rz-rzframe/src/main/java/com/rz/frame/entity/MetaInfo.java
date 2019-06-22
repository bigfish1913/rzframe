package com.rz.frame.entity;

public class MetaInfo {
	
	
	public String methodName;
	public Class<?>  requestClass;
	public Class<?> responseClass;
	
	public String getMethodName() {
		return methodName;
	}
	
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public Class<?> getRequestClass() {
		return requestClass;
	}
	
	public void setRequestClass(Class<?> requestClass) {
		this.requestClass = requestClass;
	}
	
	public Class<?> getResponseClass() {
		return responseClass;
	}
	
	public void setResponseClass(Class<?> responseClass) {
		this.responseClass = responseClass;
	}
}

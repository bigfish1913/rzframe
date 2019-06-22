package com.rz.frame.contract;

public class RzResponse {
	
	public int retCode;
	public String retMsg;
	
	public int getRetCode() {
		return retCode;
	}
	
	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}
	
	public String getRetMsg() {
		return retMsg;
	}
	
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
}

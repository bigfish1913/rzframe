package com.rz.frame.model;

import com.alibaba.fastjson.JSONObject;

public class JsonResult {
	
	private int code;
	private String message;
	private JSONObject data;
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public JSONObject getData() {
		return data;
	}
	
	public void setData(JSONObject data) {
		this.data = data;
	}
}

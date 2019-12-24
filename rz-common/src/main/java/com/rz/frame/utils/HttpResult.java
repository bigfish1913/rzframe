package com.rz.frame.utils;





import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Map;

public class HttpResult {
	private int httpCode;
	public int getHttpCode() {
		return httpCode;
	}
	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}
	
	private Map<String,String> cookie;
	
	public Map<String, String> getCookie() {
		return cookie;
	}
	
	public void setCookie(Map<String, String> cookie) {
		this.cookie = cookie;
	}
	
	private String responseBody;
	public String getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
	
	private String redirctUrl;
	
	public String getRedirctUrl() {
		return redirctUrl;
	}
	
	public void setRedirctUrl(String redirctUrl) {
		this.redirctUrl = redirctUrl;
	}
}
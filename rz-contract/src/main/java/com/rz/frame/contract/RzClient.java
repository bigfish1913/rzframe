package com.rz.frame.contract;


import com.rz.frame.utils.HttpUtils;
import com.rz.frame.utils.JsonUtils;

public class RzClient implements IRzSerivce {
	static RzClient rzClient;
	private int timeOut;
	
	public int getTimeOut() {
		return timeOut;
	}
	
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	
	public static RzClient getInstance() {
		if (rzClient == null)
			rzClient = new RzClient();
		return rzClient;
	}
	
	public static RzClient getInstance(String remoterUrl) {
		if (rzClient == null)
			rzClient = new RzClient(remoterUrl);
		return rzClient;
	}
	
	public RzClient(String url) {
		this.remoterUrl = url + "%s";
	}
	
	public RzClient() {
	
	}
	
	public String remoterUrl = "http://localhost:8080/api/execApi/%s";
	
	
	public RzResponse getRzList(RzRequset request) {
		String apiName = "getRzList";
		String finalUrl = String.format(remoterUrl, apiName);
		String result = HttpUtils.httpPost(finalUrl, JsonUtils.toJSONObject(request));
		RzResponse response = JsonUtils.toBean(result, RzResponse.class);
		return response;
		
	}
	
	
}

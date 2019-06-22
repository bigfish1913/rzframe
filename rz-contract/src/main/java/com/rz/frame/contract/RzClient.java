package com.rz.frame.contract;


import com.rz.frame.utils.HttpUtils;
import com.rz.frame.utils.JsonUtils;

public class RzClient implements IRzSerivce {
	static RzClient rzClient;
	
	public static RzClient getInstance() {
		if (rzClient == null)
			rzClient = new RzClient();
		return rzClient;
	}
	
	
	public final String url = "http://localhost:8080/api/execApi/%s";
	
 
	public RzResponse getRzList(RzRequset request) {
		String apiName = "getRzList";
		String finalUrl = String.format(url, apiName);
		String result = HttpUtils.httpPost(finalUrl, JsonUtils.toJSONObject(request));
		RzResponse response = JsonUtils.toBean(result, RzResponse.class);
		return response;
		
	}
	
	
	public static void main(String[] args) {
		RzRequset requset=new RzRequset();
		requset.setName("付润泽");
		RzResponse rzList = getInstance().getRzList(requset);
		System.out.println(JsonUtils.toJSON(rzList));
	}
	
 
	
	
}

package com.rz.frame.rzservice;

import com.rz.frame.contract.IRzSerivce;
import com.rz.frame.contract.RzRequset;
import com.rz.frame.contract.RzResponse;

public class RzMiniService implements IRzSerivce {
	public RzResponse getRzList(RzRequset request) {
		RzResponse response = new RzResponse();
		response.setRetCode(1);
		response.setRetMsg(String.format("hello %s!", request.getName()));
		return response;
	}
}

package com.rz.frame.utils;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ElasticTcpUtilsTest {
	
	@Test
	public void add() {
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("costTime",40);
		jsonObject.put("resCode",400);
		jsonObject.put("threadCount",40);
		jsonObject.put("dataChangeLastTime", LocalDateTime.now());
 		ElasticTcpUtils.add("rzresult","result",jsonObject);
	}
	
	@Test
	public void addList() {
	}
	
	@Test
	public void searchByPage() {
	}
}
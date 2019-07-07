package com.rz.frame.utils;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ElasticUtilsTest {
	
	@Test
	public void add() {
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("costTime",50);
		jsonObject.put("resCode",500);
		jsonObject.put("threadCount",50);
		jsonObject.put("dataChangeLastTime", LocalDateTime.now());
// 	ElasticUtils.add("rzresult","result",jsonObject);
	}
}
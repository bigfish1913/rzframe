package com.rz.frame;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSONObject;
import com.rz.frame.contract.RzClient;
import com.rz.frame.contract.RzRequset;
import com.rz.frame.contract.RzResponse;
// import com.rz.frame.utils.ElasticTcpUtils;
import com.rz.frame.utils.JsonUtils;
import com.rz.frame.utils.SleepUtils;
import com.rz.frame.utils.TaskUtils;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
	/**
	 * Rigorous Test :-)
	 */
	public static void main(String[] args) {
		RzRequset requset = new RzRequset();
		requset.setName("frz");
		int nThread = 20;
		while (true) {
			List<JSONObject> objectList=new ArrayList();
			TaskUtils.startWithMultiThread(nThread, () -> {
				long start = System.currentTimeMillis();
				String testUrl = "http://192.168.0.108:8080/api/execApi/";
				RzResponse response = RzClient.getInstance(testUrl).getRzList(requset);
				long end = System.currentTimeMillis();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("costTime", end - start);
				jsonObject.put("resCode", response.getRetCode());
				jsonObject.put("threadCount", nThread);
				jsonObject.put("dataChangeLastTime", LocalDateTime.now());
				objectList.add(jsonObject);
				System.out.println(String.format("Response Code:%s Cost time:%s ms", response.getRetMsg(), (end - start)));
			});
			// ElasticTcpUtils.addList("rzresult", "result", objectList);
			SleepUtils.Sleep(1000);
			
		}
		
		
	}
}

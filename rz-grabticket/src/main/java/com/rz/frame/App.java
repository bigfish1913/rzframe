package com.rz.frame;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rz.frame.dto.PassengerModel;
import com.rz.frame.utils.JsonUtils;

import java.util.*;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		JSONObject passagerObj = JsonUtils.toBean("{\"validateMessagesShowId\": \"_validatorMessage\", \"status\": true, \"httpstatus\": 200, \"data\": {\"notify_for_gat\": \"\", \"isExist\": true, \"exMsg\": \"\", \"two_isOpenClick\": [\"93\", \"95\", \"97\", \"99\"], \"other_isOpenClick\": [\"91\", \"93\", \"98\", \"99\", \"95\", \"97\"], \"normal_passengers\": [{\"passenger_name\": \"付威\", \"sex_code\": \"M\", \"sex_name\": \"男\", \"born_date\": \"1990-10-06 00:00:00\", \"country_code\": \"CN\", \"passenger_id_type_code\": \"1\", \"passenger_id_type_name\": \"中国居民身份证\", \"passenger_id_no\": \"3412***********452\", \"passenger_type\": \"1\", \"passenger_flag\": \"0\", \"passenger_type_name\": \"成人\", \"mobile_no\": \"13661862134\", \"phone_no\": \"\", \"email\": \"756091180@qq.com\", \"address\": \"\", \"postalcode\": \"\", \"first_letter\": \"\", \"recordCount\": \"6\", \"total_times\": \"99\", \"index_id\": \"0\", \"allEncStr\": \"0c09804e119c85e744b8c3df9239ba03281681b8d76882ae01e97ca3b420bd6e94a59ce9e47e9abf60fd0de32aa23814\", \"isAdult\": \"Y\", \"isYongThan10\": \"N\", \"isYongThan14\": \"N\", \"isOldThan60\": \"N\", \"gat_born_date\": \"\", \"gat_valid_date_start\": \"\", \"gat_valid_date_end\": \"\", \"gat_version\": \"\"}, {\"passenger_name\": \"付强\", \"sex_code\": \"M\", \"sex_name\": \"男\", \"born_date\": \"2015-01-14 00:00:00\", \"country_code\": \"CN\", \"passenger_id_type_code\": \"1\", \"passenger_id_type_name\": \"中国居民身份证\", \"passenger_id_no\": \"3421***********274\", \"passenger_type\": \"1\", \"passenger_flag\": \"0\", \"passenger_type_name\": \"成人\", \"mobile_no\": \"13451795127\", \"phone_no\": \"\", \"email\": \"\", \"address\": \"\", \"postalcode\": \"\", \"first_letter\": \"FQ\", \"recordCount\": \"6\", \"total_times\": \"99\", \"index_id\": \"1\", \"allEncStr\": \"fc23a243ade70c335b54283b2e9f332687ed806ca932dc500d84bc80cff06c014fd4eb12be62be587b88cbd4c1913f73\", \"isAdult\": \"Y\", \"isYongThan10\": \"N\", \"isYongThan14\": \"N\", \"isOldThan60\": \"N\", \"gat_born_date\": \"\", \"gat_valid_date_start\": \"\", \"gat_valid_date_end\": \"\", \"gat_version\": \"\"}, {\"passenger_name\": \"何师坤\", \"sex_code\": \"M\", \"sex_name\": \"男\", \"born_date\": \"2019-09-12 00:00:00\", \"country_code\": \"CN\", \"passenger_id_type_code\": \"1\", \"passenger_id_type_name\": \"中国居民身份证\", \"passenger_id_no\": \"4209***********235\", \"passenger_type\": \"1\", \"passenger_flag\": \"0\", \"passenger_type_name\": \"成人\", \"mobile_no\": \"\", \"phone_no\": \"\", \"email\": \"\", \"address\": \"\", \"postalcode\": \"\", \"first_letter\": \"HSK\", \"recordCount\": \"6\", \"total_times\": \"99\", \"index_id\": \"2\", \"allEncStr\": \"9d0fd538edc253c0ddb58147ae30a0859b959c8f9bc1068458028773c76792ed\", \"isAdult\": \"Y\", \"isYongThan10\": \"N\", \"isYongThan14\": \"N\", \"isOldThan60\": \"N\", \"gat_born_date\": \"\", \"gat_valid_date_start\": \"\", \"gat_valid_date_end\": \"\", \"gat_version\": \"\"}, {\"passenger_name\": \"胡秀梅\", \"sex_code\": \"F\", \"sex_name\": \"女\", \"born_date\": \"2019-09-12 00:00:00\", \"country_code\": \"CN\", \"passenger_id_type_code\": \"1\", \"passenger_id_type_name\": \"中国居民身份证\", \"passenger_id_no\": \"4203***********045\", \"passenger_type\": \"1\", \"passenger_flag\": \"0\", \"passenger_type_name\": \"成人\", \"mobile_no\": \"\", \"phone_no\": \"\", \"email\": \"\", \"address\": \"\", \"postalcode\": \"\", \"first_letter\": \"HXM\", \"recordCount\": \"6\", \"total_times\": \"99\", \"index_id\": \"3\", \"allEncStr\": \"0dc5a83ae86b72b0d52702f1ef265aaa026667c66d2c87d0077d612160c7d9bc\", \"isAdult\": \"Y\", \"isYongThan10\": \"N\", \"isYongThan14\": \"N\", \"isOldThan60\": \"N\", \"gat_born_date\": \"\", \"gat_valid_date_start\": \"\", \"gat_valid_date_end\": \"\", \"gat_version\": \"\"}, {\"passenger_name\": \"桑士英\", \"sex_code\": \"F\", \"sex_name\": \"女\", \"born_date\": \"2015-09-08 00:00:00\", \"country_code\": \"CN\", \"passenger_id_type_code\": \"1\", \"passenger_id_type_name\": \"中国居民身份证\", \"passenger_id_no\": \"3412***********447\", \"passenger_type\": \"1\", \"passenger_flag\": \"0\", \"passenger_type_name\": \"成人\", \"mobile_no\": \"15398103862\", \"phone_no\": \"\", \"email\": \"\", \"address\": \"\", \"postalcode\": \"\", \"first_letter\": \"SSY\", \"recordCount\": \"6\", \"total_times\": \"99\", \"index_id\": \"4\", \"allEncStr\": \"6d465432363feace7878ce9c4d05840f22385fb95ca4b4876251d80a90b60e00625e962d0551bd1373d41c5031814bd8\", \"isAdult\": \"Y\", \"isYongThan10\": \"N\", \"isYongThan14\": \"N\", \"isOldThan60\": \"N\", \"gat_born_date\": \"\", \"gat_valid_date_start\": \"\", \"gat_valid_date_end\": \"\", \"gat_version\": \"\"}, {\"passenger_name\": \"陶晓艳\", \"sex_code\": \"F\", \"sex_name\": \"女\", \"born_date\": \"2014-02-05 00:00:00\", \"country_code\": \"CN\", \"passenger_id_type_code\": \"1\", \"passenger_id_type_name\": \"中国居民身份证\", \"passenger_id_no\": \"3412***********584\", \"passenger_type\": \"1\", \"passenger_flag\": \"0\", \"passenger_type_name\": \"成人\", \"mobile_no\": \"15546022898\", \"phone_no\": \"\", \"email\": \"\", \"address\": \"\", \"postalcode\": \"\", \"first_letter\": \"TXY\", \"recordCount\": \"6\", \"total_times\": \"99\", \"index_id\": \"5\", \"allEncStr\": \"516ff6715d6888a278640fd793d92678e136e87efa430d053b3bc1d1520b2c965e2d3c09993a6689c3c4775d298be48e\", \"isAdult\": \"Y\", \"isYongThan10\": \"N\", \"isYongThan14\": \"N\", \"isOldThan60\": \"N\", \"gat_born_date\": \"\", \"gat_valid_date_start\": \"\", \"gat_valid_date_end\": \"\", \"gat_version\": \"\"} ], \"dj_passengers\": [] }, \"messages\": [], \"validateMessages\": {} }");
		JSONArray data = passagerObj.getJSONObject("data").getJSONArray("normal_passengers");
		List<PassengerModel> passengerModelList = new ArrayList<>();
		for (Object entry : data) {
			JSONObject jsonObject = (JSONObject) entry;
			HashMap<String, String> passengerModelMap = new HashMap<>();
			for (Map.Entry<String, Object> mapObj : jsonObject.entrySet()) {
				passengerModelMap.put(mapObj.getKey(), mapObj.getValue().toString());
				
			}
			PassengerModel passengerModel = new PassengerModel(passengerModelMap);
			passengerModelList.add(passengerModel);
		}
		
		
		System.out.println("");
	}
}

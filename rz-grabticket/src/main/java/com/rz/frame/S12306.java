package com.rz.frame;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rz.frame.dto.PassengerModel;
import com.rz.frame.dto.SeatModel;
import com.rz.frame.dto.TicketModel;
import com.rz.frame.utils.*;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.rz.frame.dto.Constants.LoginUrl.*;
import static com.rz.frame.dto.Constants.OrderUrl.*;
import static com.rz.frame.dto.Constants.QueryUrl.*;


public class S12306 {
	public static void main(String[] args) throws IOException {
		String ticketDate = "2020-01-16";
		HttpTicketUtils httpTicketUtils = HttpTicketUtils.getHttpClient("13661862134");
		String queryResult = httpTicketUtils.doGet(String.format(queryUrl, ticketDate, "SHH", "IMH"));
		JSONObject jsonObject = JsonUtils.toBean(queryResult);
		JSONObject data = jsonObject.getJSONObject("data");
		JSONArray result = data.getJSONArray("result");
		List<TicketModel> ticketModels = new ArrayList<>();
		for (int i = 0; i < result.size(); i++) {
			TicketModel ticketModel = new TicketModel();
			ticketModel.setInfo(result.get(i).toString().split("\\|"));
			ticketModels.add(ticketModel);
		}
		
		loginInfo();
		String checkUser = httpTicketUtils.doPost("https://kyfw.12306.cn/otn/login/checkUser", new HashMap<String, String>());
		TicketModel ticketModel = ticketModels.stream().filter(x -> x.getTrainNumber().equals("D3131")).findFirst().get();
		ticketModel.setTrainDate(ticketDate);
		HashMap<String, String> paras = new HashMap<>();
		paras.put("secretStr", ticketModel.getSecret());
		
		paras.put("train_date", ticketDate);
		paras.put("back_train_date", "2019-12-20");
		paras.put("tour_flag", "dc");
		paras.put("purpose_codes", "ADULT");
		paras.put("query_from_station_name", ticketModel.getFrom());
		paras.put("query_to_station_name", ticketModel.getTo());
		
		String submitOrder = httpTicketUtils.doPost(submitUrl, paras);
		String initDcHtml = httpTicketUtils.doPost(initDcUrl, new HashMap<>());
		String token = "";
		String keyCheck = "";
		String regex = "globalRepeatSubmitToken \\= '(.*?)';";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(initDcHtml);
		while (m.find()) {
			token = m.group(1);
		}
		regex = "'key_check_isChange':'(.*?)',";
		Pattern p1 = Pattern.compile(regex);
		Matcher m1 = p1.matcher(initDcHtml);
		while (m1.find()) {
			keyCheck = m1.group(1);
		}
		
		HashMap<String, String> passagerParas = new HashMap<>();
		passagerParas.put("_json_att", "");
		passagerParas.put("REPEAT_SUBMIT_TOKEN", token);
		
		String passager = httpTicketUtils.doPost(passengerDTOsUrl, passagerParas);
		List<PassengerModel> passengerModelList = getPassengerModelList(passager);
		PassengerModel passengerModel = passengerModelList.stream().filter(x -> x.getPassengerName().equals("付威")).findFirst().get();
		
		SeatModel seatModel = ticketModel.getSeat().stream().filter(x -> x.getSeatLevel().getName().equals("二等座")).findFirst().get();
		List<NameValuePair> formparams = new ArrayList<>();
		
		formparams.add(new BasicNameValuePair("bed_level_order_num", "000000000000000000000000000000"));
		formparams.add(new BasicNameValuePair("cancel_flag", "2"));
		formparams.add(new BasicNameValuePair("whatsSelect", "2"));
		formparams.add(new BasicNameValuePair("_json_att", ""));
		formparams.add(new BasicNameValuePair("tour_flag", "dc"));
		formparams.add(new BasicNameValuePair("randCode", ""));
		formparams.add(new BasicNameValuePair("passengerTicketStr", passengerModel.getPassengerTicketStr(passengerModel, seatModel)));
		formparams.add(new BasicNameValuePair("REPEAT_SUBMIT_TOKEN", token));
		formparams.add(new BasicNameValuePair("getOldPassengerStr", passengerModel.getOldPassengerStr(passengerModel)));
		String checkOrder = httpTicketUtils.doPost(checkOrderInfoUrl, formparams);
		getQueueCount(ticketModel, token, seatModel);
		confirmSingleForQueue(ticketModel, keyCheck, passengerModel, seatModel, token);
		System.out.println(checkOrder);
	}
	
	private static void loginInfo() throws IOException {
		HttpTicketUtils httpTicketUtils = HttpTicketUtils.getHttpClient("13661862134");
		List<NameValuePair> uamtkStatic = new ArrayList<>();
		uamtkStatic.add(new BasicNameValuePair("appid", "otn"));
		
		String result = httpTicketUtils.doPost(uamtk_static, uamtkStatic);
		
		RzLogger.info("1. 检测登录结果：", result);
		//1. https://kyfw.12306.cn/otn/login/conf
		String confHttpResult = httpTicketUtils.doPost(conf, new HashMap<String, String>());
		
		RzLogger.info("2. conf:", confHttpResult);
		
		RzLogger.info("开始获得验证码");
		
		//4. 打码captcha-image64
		
		String imgStr = httpTicketUtils.doGet(String.format(captchaImg, Math.random()));
		String imgFormat = RegexUtils.matchOne("\\{.+\\}", imgStr);
		JSONObject imgObj = JsonUtils.toBean(imgFormat);
		Base64ImgUtils.base64ChangeImage(imgObj.getString("image"), "E:/temp/1.jpg");
		RzLogger.info("验证码获取成功，开始打码");
		
		//5、获得打码结果
		StringBuilder position = getPosition(imgObj);
		
		RzLogger.info("打码结果:" + position);
		
		String checkResult = httpTicketUtils.doGet(String.format(captchaCheck, position.toString(), Math.random()));
		String checkFormat = RegexUtils.matchOne("\\{.+\\}", checkResult);
		JSONObject checkObj = (JSONObject) JsonUtils.toBean(checkFormat);
		if (!checkObj.getString("result_code").equals("4")) {
			RzLogger.info("验证码校验失败，请重试");
			return;
		}
		RzLogger.info("验证码校验成功，开始进行登录");
		
		
		List<NameValuePair> loginParas = new ArrayList<>();
		loginParas.add(new BasicNameValuePair("username", "13661862134"));
		loginParas.add(new BasicNameValuePair("password", "Abc12345"));
		loginParas.add(new BasicNameValuePair("appid", "otn"));
		loginParas.add(new BasicNameValuePair("answer", position.toString()));
		
		String loginResult = httpTicketUtils.doPost(webLogin, loginParas);
		RzLogger.info("登陆结果:", loginResult);
		
		String userLoginResult = httpTicketUtils.doGet(userLogin);
		RzLogger.info("UserLogResult:", userLoginResult);
		
		List<NameValuePair> uamtkformparams = new ArrayList<>();
		uamtkformparams.add(new BasicNameValuePair("appid", "otn"));
		
		String uamtkResult = httpTicketUtils.doPost(uamtk, uamtkformparams);
		RzLogger.info("uamtk结果：", uamtkResult);
		
		
		JSONObject uamtk = JsonUtils.toBean(uamtkResult);
		
		List<NameValuePair> uamauthclientformparams = new ArrayList<>();
		uamauthclientformparams.add(new BasicNameValuePair("tk", uamtk.getString("newapptk")));
		
		String uamauthResult = httpTicketUtils.doPost(uamauthclient, uamauthclientformparams);
		RzLogger.info("uamauth获得登录名：", uamauthResult);
	}
	
	private static void getQueueCount(TicketModel ticketModel, String token, SeatModel seatModel) {
		List<NameValuePair> formparams = new ArrayList<>();
		
		formparams.add(new BasicNameValuePair("fromStationTelecode", ticketModel.getFrom()));
		formparams.add(new BasicNameValuePair("toStationTelecode", ticketModel.getTo()));
		formparams.add(new BasicNameValuePair("leftTicket", ticketModel.getLeftTicket()));
		formparams.add(new BasicNameValuePair("purpose_codes", "00"));
		formparams.add(new BasicNameValuePair("REPEAT_SUBMIT_TOKEN", token));
		formparams.add(new BasicNameValuePair("seatType", seatModel.getSeatLevel().getCode()));
		formparams.add(new BasicNameValuePair("stationTrainCode", ticketModel.getTrainNumber()));
		formparams.add(new BasicNameValuePair("train_date", DateUtils.getGMT(ticketModel.getTrainDate())));
		formparams.add(new BasicNameValuePair("train_location", ticketModel.getTrainLocation()));
		formparams.add(new BasicNameValuePair("train_no", ticketModel.getTrainCode()));
		formparams.add(new BasicNameValuePair("_json_att", ""));
		HttpTicketUtils httpClient = HttpTicketUtils.getHttpClient("13661862134");
		String s = httpClient.doPost(getQueueCountUrl, formparams);
		RzLogger.info(s);
		
	}
	
	public static void confirmSingleForQueue(TicketModel ticketModel, String keyCheck, PassengerModel passengerModel, SeatModel seatModel, String token) {
		
		
		List<NameValuePair> formparams = new ArrayList<>();
		formparams.add(new BasicNameValuePair("dwAll", "N"));
		formparams.add(new BasicNameValuePair("purpose_codes", "00"));
		formparams.add(new BasicNameValuePair("key_check_isChange", keyCheck));
		formparams.add(new BasicNameValuePair("_json_att", ""));
		formparams.add(new BasicNameValuePair("leftTicketStr", ticketModel.getLeftTicket()));
		formparams.add(new BasicNameValuePair("train_location", ticketModel.getTrainLocation()));
		formparams.add(new BasicNameValuePair("choose_seats", ""));
		formparams.add(new BasicNameValuePair("whatsSelect", "1"));
		formparams.add(new BasicNameValuePair("roomType", "00"));
		formparams.add(new BasicNameValuePair("seatDetailType", "000"));
		formparams.add(new BasicNameValuePair("randCode", ""));
		formparams.add(new BasicNameValuePair("passengerTicketStr", passengerModel.getPassengerTicketStr(passengerModel, seatModel)));
		formparams.add(new BasicNameValuePair("REPEAT_SUBMIT_TOKEN", token));
		formparams.add(new BasicNameValuePair("getOldPassengerStr", passengerModel.getOldPassengerStr(passengerModel)));
		HttpTicketUtils httpClient = HttpTicketUtils.getHttpClient("13661862134");
		String s = httpClient.doPost(confirmSingleForQueueUrl, formparams);
		RzLogger.info(s);
	}
	
	private static StringBuilder getPosition(JSONObject imgObj) throws IOException {
		String markURL = "https://www.markcaptcha.com/Mark12306Captcha/mark/captcha";
		HttpPost captchaHttp = new HttpPost(markURL);
		Map<String, String> captchaDataMap = new HashMap<>();
		captchaDataMap.put("CaptchaBase64Str", imgObj.getString("image"));
		InputStreamEntity printCodeParaEntity = new InputStreamEntity(new ByteArrayInputStream(JsonUtils.serializeObject(captchaDataMap).getBytes()));
		captchaHttp.setEntity(printCodeParaEntity);
		CloseableHttpResponse printResult = HttpClients.createDefault().execute(captchaHttp);
		
		JSONObject json = JsonUtils.toBean(getEntityContent(printResult.getEntity()));
		JSONArray pArray = json.getJSONArray("result");
		StringBuilder position = new StringBuilder();
		for (int i = 0; i < pArray.size(); i++) {
			position.append(pArray.get(i).toString().replace("[", "").replace("]", ""));
			if (i != pArray.size() - 1) {
				position.append(",");
			}
		}
		return position;
	}
	
	
	private static List<PassengerModel> getPassengerModelList(String passagerStr) {
		JSONObject passagerObj = JsonUtils.toBean(passagerStr);
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
		return passengerModelList;
		
	}
	
	//	public static void main(String[] args) {
	//		System.out.println(URLEncoder.encode(getPosition("7")));
	//
	//
	//	}
	//
	//	protected MarkCaptchaReturnResult markCaptcha(String captchaBase64Str) {
	//		// get a new session
	//		CloseableHttpClient markHttpClient = HttpTools.getSession(30000);
	//		// set requests params
	//		String markURL = "https://www.markcaptcha.com/Mark12306Captcha/mark/captcha";
	//		HttpPost httpPostMark = HttpTools.setRequestHeader(new HttpPost(markURL), false, true, false);
	//		Map<String, String> captchaDataMap = new HashMap<>();
	//		captchaDataMap.put("CaptchaBase64Str", captchaBase64Str);
	//		httpPostMark.setEntity(HttpTools.doPostDataFromJson(captchaDataMap));
	//		CloseableHttpResponse response = null;
	//		MarkCaptchaReturnResult markCaptchaReturnResult = new MarkCaptchaReturnResult();
	//		try {
	//			response = markHttpClient.execute(httpPostMark);
	//			if (response.getStatusLine().getStatusCode() == HTTP_SUCCESS) {
	//				String responseText = HttpTools.responseToString(response);
	//				JSONObject checkJsonData = JSONObject.parseObject(responseText);
	//				// mark result json
	//				Boolean status = checkJsonData.getBoolean("status");
	//				String result = checkJsonData.getString("result");
	//				Integer resultCode = checkJsonData.getInteger("resultCode");
	//				String message = checkJsonData.getString("message");
	//				// mark success
	//				if (status && resultCode == 0) {
	//					markCaptchaReturnResult.setStatus(true);
	//					markCaptchaReturnResult.setMessage(message);
	//					markCaptchaReturnResult.setSession(null);
	//					markCaptchaReturnResult.setResult(markResultHandle(result));
	//					return markCaptchaReturnResult;
	//				}
	//			}
	//		} catch (Exception e) {
	//			log.error("mark captcha time out");
	//		} finally {
	//			if (response != null) {
	//				try {
	//					response.close();
	//				} catch (IOException e) {
	//					log.error(e.getMessage());
	//				}
	//			}
	//			try {
	//				markHttpClient.close();
	//			} catch (IOException e) {
	//				log.error(e.getMessage());
	//			}
	//		}
	//		return failedMarkCaptchaReturnResult(MARK_CAPTCHA_FAILED);
	//	}
	static String getEntityContent(HttpEntity httpEntity) {
		
		try {
			String result = EntityUtils.toString(httpEntity, "UTF-8");
			EntityUtils.consume(httpEntity);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
		
	}
	
	static String getPosition(String pos) {
		String[] posArr = pos.split(",");
		String[] poxArr = new String[]{"25-50", "100-125", "150-225", "250-275"};
		String[] poyArr = new String[]{"25-50", "100-125"};
		int total = poxArr.length * poyArr.length;
		String posStr = "";
		for (int i = 0; i < posArr.length; i++) {
			Integer index = Integer.valueOf(posArr[i]) - 1;
			if (index > total) {
				continue;
			}
			int xIndex = index % poxArr.length;
			int yIndex = index / poxArr.length;
			String idxRang = poxArr[xIndex];
			String idyRang = poyArr[yIndex];
			
			int rx = RandomUtils.getNumberInt(idxRang);
			int ry = RandomUtils.getNumberInt(idyRang);
			if (StringUtils.isEmpty(posStr)) {
				posStr += String.format("%s,%s", rx, ry);
				continue;
			}
			posStr += String.format(",%s,%s", rx, ry);
		}
		return posStr;
		
	}
}

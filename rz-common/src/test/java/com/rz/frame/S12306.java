package com.rz.frame;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rz.frame.utils.*;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rz.frame.utils.GlobalConstant.LoginUrl.*;
import static com.rz.frame.utils.GlobalConstant.QueryUrl.queryUrl;

public class S12306 {
	public static void main(String[] args) throws IOException {
//		HttpTicketUtils httpTicketUtils = HttpTicketUtils.getHttpClient("13661862134");
//		String queryResult = httpTicketUtils.doGet(String.format(queryUrl,"2020-01-18", "SHH", "IMH"));
//		JSONObject jsonObject = JsonUtils.toBean(queryResult);
//
//		System.out.println(queryResult);
		RzLogger.info("1. 检测登录结果：{}", 12);
		
	}
	
	private static void loginInfo() throws IOException {
		HttpTicketUtils httpTicketUtils = HttpTicketUtils.getHttpClient("13661862134");
		List<NameValuePair> uamtkStatic = new ArrayList<>();
		uamtkStatic.add(new BasicNameValuePair("appid", "otn"));
		
		String result = httpTicketUtils.doPost(uamtk_static, uamtkStatic);
		
		RzLogger.info("1. 检测登录结果：", result);
		//1. https://kyfw.12306.cn/otn/login/conf
		String confHttpResult = httpTicketUtils.doPost(conf, new HashMap<>());
		
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

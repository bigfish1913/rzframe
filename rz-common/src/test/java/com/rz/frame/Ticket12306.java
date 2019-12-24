package com.rz.frame;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rz.frame.utils.Base64ImgUtils;
import com.rz.frame.utils.JsonUtils;
import com.rz.frame.utils.RegexUtils;
import com.rz.frame.utils.RzLogger;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
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

import static com.rz.frame.S12306.getEntityContent;

public class Ticket12306 {
	public static void main(String[] args) throws IOException {
		
		HttpPost httpPost = new HttpPost("https://kyfw.12306.cn/passport/web/auth/uamtk-static");
		List<NameValuePair> formparams = new ArrayList<>();
		formparams.add(new BasicNameValuePair("appid", "otn"));
		UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		httpPost.setEntity(urlEncodedFormEntity);
		
		BasicCookieStore basicCookieStore = new BasicCookieStore();
		BasicClientCookie expCookie = new BasicClientCookie("RAIL_EXPIRATION", "1577035780226");
		expCookie.setDomain("kyfw.12306.cn");
		expCookie.setPath("/");
		basicCookieStore.addCookie(expCookie);
		
		BasicClientCookie dfCookie = new BasicClientCookie("RAIL_DEVICEID", "HLBotQl0gb_tMA0w8wQYyhlydh4O8rNBYuA6ftmzDouK0LzJ0FQM_AtphhoJyFtRfxkEydzIvQ4_KBcBUeeQ9ybMJVKOXrb_3nXgSxtjXPzRXezYHjIrv3oR3dC2rMjackA-w7QyXfUvqxSpm2_tLZJQUZON38TQ");
		dfCookie.setDomain("kyfw.12306.cn");
		dfCookie.setPath("/");
		basicCookieStore.addCookie(dfCookie);
		httpPost.addHeader(new BasicHeader("Origin", "https://kyfw.12306.cn"));
		httpPost.addHeader(new BasicHeader(HttpHeaders.REFERER, "https://kyfw.12306.cn"));
		httpPost.addHeader(new BasicHeader(HttpHeaders.HOST, "kyfw.12306.cn"));
		CloseableHttpClient closeableHttpClient = HttpClients.custom().setDefaultCookieStore(basicCookieStore).build();
		
		HttpResponse response = closeableHttpClient.execute(httpPost);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity, "UTF-8");
		EntityUtils.consume(httpEntity);
		RzLogger.info("检测登录结果：", result);
		
		//1. https://kyfw.12306.cn/otn/login/conf
		HttpPost conf = new HttpPost("https://kyfw.12306.cn/otn/login/conf");
		CloseableHttpResponse confHttpResult = closeableHttpClient.execute(conf);
		HttpEntity confHttpEntity = confHttpResult.getEntity();
		RzLogger.info("conf:", getEntityContent(confHttpEntity));
		//		//2. https://kyfw.12306.cn/otn/index12306/getLoginBanner
		//		HttpGet getLoginBanner = new HttpGet("https://kyfw.12306.cn/otn/index12306/getLoginBanner");
		//		CloseableHttpResponse bannerHttpResult = closeableHttpClient.execute(getLoginBanner);
		//		RzLogger.info("LoginBanner:", getEntityContent(bannerHttpResult.getEntity()));
		//
		//3. https://kyfw.12306.cn/passport/web/auth/uamtk-static
		
		
		RzLogger.info("获得验证码");
		
		//4. 打码captcha-image64
		
		HttpGet imgHttpGet = new HttpGet(String.format("https://kyfw.12306.cn/passport/captcha/captcha-image64?login_site=E&module=login&rand=sjrand&_=%s", Math.random()));
		HttpResponse imgResponse = closeableHttpClient.execute(imgHttpGet);
		
		String imgStr = getEntityContent(imgResponse.getEntity());
		String imgFormat = RegexUtils.matchOne("\\{.+\\}", imgStr);
		JSONObject imgObj = JsonUtils.toBean(imgFormat);
		Base64ImgUtils.base64ChangeImage(imgObj.getString("image"), "E:/temp/1.jpg");
		RzLogger.info("验证码获取成功，开始打码");
		
		//5、获得打码结果
		StringBuilder position = getPosition(imgObj);
		
		RzLogger.info("打码结果:" + position);
		HttpGet checkHttp = new HttpGet(String.format("https://kyfw.12306.cn/passport/captcha/captcha-check?answer=%s&rand=sjrand&login_site=E&_=%s", URLEncoder.encode(position.toString()), System.currentTimeMillis()));
		
		checkHttp.addHeader(new BasicHeader("Origin", "https://kyfw.12306.cn"));
		checkHttp.addHeader(new BasicHeader(HttpHeaders.REFERER, "https://kyfw.12306.cn"));
		checkHttp.addHeader(new BasicHeader(HttpHeaders.HOST, "kyfw.12306.cn"));
		
		CloseableHttpResponse checkResult = closeableHttpClient.execute(checkHttp);
		String checkFormat = RegexUtils.matchOne("\\{.+\\}", getEntityContent(checkResult.getEntity()));
		JSONObject checkObj = (JSONObject) JsonUtils.toBean(checkFormat);
		if (!checkObj.getString("result_code").equals("4")) {
			RzLogger.info("验证码校验失败，请重试");
			return;
			
		}
		RzLogger.info("验证码校验成功，开始进行登录");
		
		
		HttpPost loginPost = new HttpPost("https://kyfw.12306.cn/passport/web/login");
		List<NameValuePair> loginParas = new ArrayList<>();
		loginParas.add(new BasicNameValuePair("username", "13661862134"));
		loginParas.add(new BasicNameValuePair("password", "Abc12345"));
		loginParas.add(new BasicNameValuePair("appid", "otn"));
		loginParas.add(new BasicNameValuePair("answer", position.toString()));
		
		UrlEncodedFormEntity loginUrlEntity = new UrlEncodedFormEntity(loginParas, Consts.UTF_8);
		loginPost.setEntity(loginUrlEntity);
		
		
		CloseableHttpResponse loginResult = closeableHttpClient.execute(loginPost);
		RzLogger.info("登陆结果:", getEntityContent(loginResult.getEntity()));
		
		HttpGet userLogin = new HttpGet("https://kyfw.12306.cn/otn/login/userLogin");
		CloseableHttpResponse userLoginResult = closeableHttpClient.execute(userLogin);
		RzLogger.info("UserLogResult:", userLoginResult.getStatusLine().getStatusCode()==HttpStatus.SC_OK);
		
		HttpPost uamtkPost = new HttpPost("https://kyfw.12306.cn/passport/web/auth/uamtk");
		uamtkPost.addHeader(new BasicHeader("Origin", "https://kyfw.12306.cn"));
		uamtkPost.addHeader(new BasicHeader(HttpHeaders.REFERER, "https://kyfw.12306.cn"));
		uamtkPost.addHeader(new BasicHeader(HttpHeaders.HOST, "kyfw.12306.cn"));
		List<NameValuePair> uamtkformparams = new ArrayList<>();
		uamtkformparams.add(new BasicNameValuePair("appid", "otn"));
		UrlEncodedFormEntity uamtkPosturlEncodedFormEntity = new UrlEncodedFormEntity(uamtkformparams, Consts.UTF_8);
		uamtkPost.setEntity(uamtkPosturlEncodedFormEntity);
		CloseableHttpResponse uamtkResult = closeableHttpClient.execute(uamtkPost);
		String entityContent = getEntityContent(uamtkResult.getEntity());
		JSONObject uamtk = JsonUtils.toBean(entityContent);
		RzLogger.info("uamtk结果：", entityContent);
		
		HttpPost uamauthclientPost = new HttpPost("https://kyfw.12306.cn/otn/uamauthclient");
		uamauthclientPost.addHeader(new BasicHeader("Origin", "https://kyfw.12306.cn"));
		uamauthclientPost.addHeader(new BasicHeader(HttpHeaders.REFERER, "https://kyfw.12306.cn"));
		uamauthclientPost.addHeader(new BasicHeader(HttpHeaders.HOST, "kyfw.12306.cn"));
		List<NameValuePair> uamauthclientformparams = new ArrayList<>();
		uamauthclientformparams.add(new BasicNameValuePair("tk", uamtk.getString("newapptk")));
		UrlEncodedFormEntity uamauthclienturlEncodedFormEntity = new UrlEncodedFormEntity(uamauthclientformparams, Consts.UTF_8);
		uamauthclientPost.setEntity(uamauthclienturlEncodedFormEntity);
		uamauthclientPost.addHeader(new BasicHeader("Origin", "https://kyfw.12306.cn"));
		uamauthclientPost.addHeader(new BasicHeader(HttpHeaders.REFERER, "https://kyfw.12306.cn"));
		uamauthclientPost.addHeader(new BasicHeader(HttpHeaders.HOST, "kyfw.12306.cn"));
		CloseableHttpResponse uamauthResult = closeableHttpClient.execute(uamauthclientPost);
		RzLogger.info("uamauth获得登录名：", getEntityContent(uamauthResult.getEntity()));
		
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
	
}

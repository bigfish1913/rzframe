package com.rz.frame.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class HttpTicketUtils {
	
	private static ConcurrentHashMap<String, HttpTicketUtils> concurrentHashMap = new ConcurrentHashMap<>();
	
	private CloseableHttpClient httpClient;
	
	public HttpTicketUtils() {
		httpClient = HttpClients.createDefault();
	}
	
	public HttpTicketUtils(CloseableHttpClient closeableHttpClient) {
		httpClient = closeableHttpClient;
	}
	
	public String doGet(String url) {
		
		try {
			HttpResponse httpResponse = this.doAction(new HttpGet(url));
			
			HttpEntity entity = httpResponse.getEntity();
			String content = entityToString(entity);
			
			EntityUtils.consume(entity);
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String doGet(HttpGet httpGet) {
		
		try {
			HttpResponse httpResponse = this.doAction(httpGet);
			
			HttpEntity entity = httpResponse.getEntity();
			String content = entityToString(entity);
			
			EntityUtils.consume(entity);
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String doPost(String url, List<NameValuePair> paras) {
		try {
			HttpPost httpPost = new HttpPost(url);
			
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(paras, "UTF-8");
			httpPost.setEntity(urlEncodedFormEntity);
			
			HttpResponse httpResponse = doAction(httpPost);
			HttpEntity entity = httpResponse.getEntity();
			String content = entityToString(entity);
			EntityUtils.consume(entity);
			return content;
		} catch (Exception e) {
			return null;
		}
	}
	
	public String doPost(String url, Map<String, String> paras) {
		try {
			HttpPost httpPost = new HttpPost(url);
			
			List<NameValuePair> formparams = null;
			if (paras != null && paras.size() > 0) {
				formparams = new ArrayList<>();
				for (Map.Entry<String, String> para : paras.entrySet()) {
					formparams.add(new BasicNameValuePair(para.getKey(), para.getValue()));
				}
				UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
				httpPost.setEntity(urlEncodedFormEntity);
			}
			HttpResponse httpResponse = doAction(httpPost);
			HttpEntity entity = httpResponse.getEntity();
			String content = entityToString(entity);
			EntityUtils.consume(entity);
			return content;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static HttpTicketUtils getHttpClient() {
		return getHttpClient("");
	}
	
	public static HttpTicketUtils getHttpClient(String userName) {
		if (StringUtils.isEmpty(userName)) {
			return new HttpTicketUtils();
		}
		if (concurrentHashMap.containsKey(userName)) {
			return concurrentHashMap.get(userName);
		}
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(getBasicCookieStore()).build();
		HttpTicketUtils httpTicketUtils = new HttpTicketUtils(httpClient);
		concurrentHashMap.put(userName, httpTicketUtils);
		return httpTicketUtils;
	}
	
	private HttpResponse doAction(HttpRequestBase httpRequestBase) {
		try {
			httpRequestBase.addHeader(new BasicHeader("Origin", "https://kyfw.12306.cn"));
			httpRequestBase.addHeader(new BasicHeader(HttpHeaders.REFERER, "https://kyfw.12306.cn"));
			httpRequestBase.addHeader(new BasicHeader(HttpHeaders.HOST, "kyfw.12306.cn"));
			return httpClient.execute(httpRequestBase);
			
		} catch (IOException e) {
			RzLogger.error("请求异常：{},{}", httpRequestBase.getURI(), e.getMessage());
		}
		return null;
	}
	
	private static BasicCookieStore getBasicCookieStore() {
		BasicCookieStore basicCookieStore = new BasicCookieStore();
		BasicClientCookie expCookie = new BasicClientCookie("RAIL_EXPIRATION", "1577035780226");
		expCookie.setDomain("kyfw.12306.cn");
		expCookie.setPath("/");
		basicCookieStore.addCookie(expCookie);
		
		BasicClientCookie dfCookie = new BasicClientCookie("RAIL_DEVICEID", "HLBotQl0gb_tMA0w8wQYyhlydh4O8rNBYuA6ftmzDouK0LzJ0FQM_AtphhoJyFtRfxkEydzIvQ4_KBcBUeeQ9ybMJVKOXrb_3nXgSxtjXPzRXezYHjIrv3oR3dC2rMjackA-w7QyXfUvqxSpm2_tLZJQUZON38TQ");
		dfCookie.setDomain("kyfw.12306.cn");
		dfCookie.setPath("/");
		basicCookieStore.addCookie(dfCookie);
		return basicCookieStore;
	}
	
	//	private static List<BasicHeader> getBasicHeader() {
	//		List<BasicHeader> headerList = new ArrayList<>();
	//		headerList.add(new BasicHeader("Origin", "https://kyfw.12306.cn"));
	//		headerList.add(new BasicHeader(HttpHeaders.REFERER, "https://kyfw.12306.cn"));
	//		headerList.add(new BasicHeader(HttpHeaders.HOST, "kyfw.12306.cn"));
	//		return headerList;
	//	}
	
	private static String entityToString(HttpEntity entity) {
		try {
			String result = null;
			if (entity == null) {
				return null;
			}
			long lenth = entity.getContentLength();
			if (lenth != -1 && lenth < 2048) {
				result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
			} else {
				InputStreamReader reader1 = new InputStreamReader(entity.getContent(), StandardCharsets.UTF_8);
				CharArrayBuffer buffer = new CharArrayBuffer(2048);
				char[] tmp = new char[1024];
				int l;
				while ((l = reader1.read(tmp)) != -1) {
					buffer.append(tmp, 0, l);
				}
				result = buffer.toString();
			}
			return result;
		} catch (Exception ex) {
			RzLogger.error("转换失败", ex);
			return "";
		}
		
	}
}

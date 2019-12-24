package com.rz.frame.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;

import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class HttpUtils {
	
	public String get(HttpGet httpGet) {
		HttpResponse httpResponse = this.doAction(httpGet, null, null );
		return entityToString(httpResponse.getEntity());
	}
	
	
	public String doPost(String url, Map<String, String> paras) {
		HttpPost httpPost = new HttpPost(url);
		HttpResponse httpResponse = doPost(httpPost, null, null);
		return entityToString(httpResponse.getEntity());
	}
	
	public String doPost(String url) {
		HttpPost httpPost = new HttpPost(url);
		HttpResponse httpResponse = doPost(httpPost, null, null);
		return entityToString(httpResponse.getEntity());
	}
	
	public String doPost(HttpPost httpPost) {
		HttpResponse httpResponse = doPost(httpPost, null, null);
		return entityToString(httpResponse.getEntity());
	}
	
	public HttpResponse doPostWithResponse(HttpPost httpPost) {
		return doPost(httpPost, null, null);
	}
	
	public HttpResponse doPost(HttpPost httpPost, Map<String, String> paras,Map<String,String> header) {
		try {
			List<NameValuePair> formparams = null;
			if (paras != null && paras.size() > 0) {
				formparams = new ArrayList<>();
				for (Map.Entry<String, String> para : paras.entrySet()) {
					formparams.add(new BasicNameValuePair(para.getKey(), para.getValue()));
				}
				UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
				httpPost.setEntity(urlEncodedFormEntity);
			}
			return this.doAction(httpPost, header, null);
		} catch (IOException e) {
			RzLogger.error("请求异常：{},{}", httpPost.getURI(), e.getMessage());
			return null;
		}
	}
	
	private HttpResponse doAction(HttpRequestBase httpRequestBase, Map<String, String> header, BasicCookieStore basicCookieStore) {
		try {
			if (header != null && header.size() > 0) {
				for (Map.Entry<String, String> head : header.entrySet()) {
					httpRequestBase.addHeader(new BasicHeader(head.getKey(), head.getValue()));
				}
			}
			HttpClientBuilder httpClientBuilder = HttpClients.custom();
			if (basicCookieStore != null) {
				httpClientBuilder.setDefaultCookieStore(basicCookieStore);
			}
			CloseableHttpClient httpClient = httpClientBuilder.build();
			return httpClient.execute(httpRequestBase);
			
		} catch (IOException e) {
			RzLogger.error("请求异常：{},{}", httpRequestBase.getURI(), e.getMessage());
		}
		return null;
	}
	
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
	
	public static String getSessionId() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
	}
}

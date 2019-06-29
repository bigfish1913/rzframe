package com.rz.frame.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class HttpUtils {
	
	/**
	 * get请求，参数拼接在地址上
	 *
	 * @param url 请求地址加参数
	 * @return 响应
	 */
	public static String httpGet(String url) {
		String result = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(get);
			if (response != null && response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				result = entityToString(entity);
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 发送post请求，参数用map接收
	 *
	 * @param url 地址
	 * @param map 参数
	 * @return 返回值
	 */
	public static String httpPost(String url, Map<String, String> map) {
		String result = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		CloseableHttpResponse response = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
			response = httpClient.execute(post);
			if (response != null && response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				result = entityToString(entity);
			}
			return result;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	public static String httpPost(String url, JSON json) {
		String result = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		
		CloseableHttpResponse response = null;
		try {
			StringEntity postEntity = new StringEntity(json.toString(), Charset.forName("UTF-8"));
			postEntity.setContentEncoding("UTF-8");
			// 发送Json格式的数据请求
			postEntity.setContentType("application/json");
			post.setEntity(postEntity);
			response = httpClient.execute(post);
			if (response != null && response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				result = entityToString(entity);
			}
			return result;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	
	public static String httpPost(String url, JSON json,int timeOut) {
		String result = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		
		CloseableHttpResponse response = null;
		try {
			StringEntity postEntity = new StringEntity(json.toString(), Charset.forName("UTF-8"));
			postEntity.setContentEncoding("UTF-8");
			// 发送Json格式的数据请求
			postEntity.setContentType("application/json");
			post.setEntity(postEntity);
			
			response = httpClient.execute(post);
			if (response != null && response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				result = entityToString(entity);
			}
			return result;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	
	private static String entityToString(HttpEntity entity) throws IOException {
		String result = null;
		if (entity != null) {
			long lenth = entity.getContentLength();
			if (lenth != -1 && lenth < 2048) {
				result = EntityUtils.toString(entity, "UTF-8");
			} else {
				InputStreamReader reader1 = new InputStreamReader(entity.getContent(), "UTF-8");
				CharArrayBuffer buffer = new CharArrayBuffer(2048);
				char[] tmp = new char[1024];
				int l;
				while ((l = reader1.read(tmp)) != -1) {
					buffer.append(tmp, 0, l);
				}
				result = buffer.toString();
			}
		}
		return result;
	}
	
	// 构建唯一会话Id
	public static String getSessionId() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
	}
}

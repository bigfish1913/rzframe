package com.rz.frame.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

public class HttpActionUtils {
	public final static String _Proxy = "180.153.144.138:8800";
	
	public static HttpResult httpConntect(String httpUrl, String methodType, String params, Charset charset, int timeOut, Map<String, String> mapHeader, String proxyUrl, String cookie, boolean isAutoRedirect) {
		HttpURLConnection connection = null;
		InputStream is = null;
		BufferedReader br = null;
		OutputStream outputStream = null;
		
		try {
			// 创建远程url连接对象
			URL url = new URL(httpUrl);
			// 通过远程url连接对象打开一个连接，强转成httpURLConnection类
			if (StringUtils.isNotEmpty(proxyUrl)) {
				String[] ary = proxyUrl.split(":");
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ary[0], Integer.valueOf(ary[1])));
				connection = (HttpURLConnection) url.openConnection(proxy);
			} else {
				connection = (HttpURLConnection) url.openConnection();
			}
			if (charset == null)
				charset = Charset.forName("UTF-8");
			connection.setDoOutput(true);// 使用 URL 连接进行输出
			connection.setDoInput(true);// 使用 URL 连接进行输入
			connection.setUseCaches(false);// 忽略缓存
			connection.setInstanceFollowRedirects(isAutoRedirect);
			// 设置连接方式
			connection.setRequestMethod(methodType);
			// 设置连接主机服务器的超时时间：15000毫秒
			connection.setConnectTimeout(timeOut);
			// 设置读取远程返回的数据时间：60000毫秒
			connection.setReadTimeout(timeOut);
			
			if (StringUtils.isNotEmpty(cookie)) {
				connection.setRequestProperty("Cookie", cookie);
			}
			if (mapHeader != null) {
				for (Map.Entry<String, String> entry : mapHeader.entrySet()) {
					connection.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			if ("GET".equals(methodType.toUpperCase()))
				connection.connect();
			if ("POST".equals(methodType.toUpperCase())) {
				connection.setRequestMethod("POST");
				if (StringUtils.isNotEmpty(params)) {
					byte[] requestBytes = params.getBytes(charset);
					
					connection.setRequestProperty("content-length", String.valueOf(requestBytes.length));
					outputStream = connection.getOutputStream();
					outputStream.write(requestBytes, 0, requestBytes.length);
				} else {
					connection.setRequestProperty("content-length", "0");
					outputStream = connection.getOutputStream();
					outputStream.write("".getBytes(charset), 0, 0);
				}
			}
			// 发送请求
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			StringBuilder sbf = new StringBuilder();
			is = connection.getInputStream();
			String responseEncoding = connection.getHeaderField("Content-Encoding");
			if ("gzip".equals(responseEncoding)) {
				outStream = decompressToStream(is);
			} else {
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					outStream.write(buffer, 0, len);
				}
			}
			byte[] bytes = outStream.toByteArray();
			
			sbf.append(new String(bytes));
			HttpResult httpResult = new HttpResult();
			httpResult.setHttpCode(connection.getResponseCode());
			httpResult.setCookie(getCookie(connection));
			httpResult.setResponseBody(sbf.toString());
			return httpResult;
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				connection.disconnect();// 关闭远程连接
			}
			
		}
		return null;
	}
	
	public static HttpResult doGet(String url) {
		HttpResult httpResult = httpConntect(url, "GET", null, null, 2000, null, _Proxy, null, false);
		return httpResult;
	}
	
	public static HttpResult doGet(String url, String cookie) {
		HttpResult httpResult = httpConntect(url, "GET", null, null, 2000, null, _Proxy, cookie, false);
		return httpResult;
	}
	
	public static HttpResult doGet(String url, Map<String, String> header, Map<String, String> cookie) {
		HttpResult httpResult = httpConntect(url, "GET", null, null, 2000, header, _Proxy, covertCookie(cookie), false);
		return httpResult;
	}
	
	public static HttpResult doGet(String url, Map<String, String> header) {
		HttpResult httpResult = httpConntect(url, "GET", null, null, 2000, header, null, null, false);
		return httpResult;
	}
	
	public static HttpResult doGet(String url, Map<String, String> header, String cookie) {
		HttpResult httpResult = httpConntect(url, "GET", null, null, 2000, header, _Proxy, cookie, false);
		return httpResult;
	}
	
	public static HttpResult doPost(String url, String params, String cookie) {
		HttpResult httpResult = httpConntect(url, "POST", params, null, 2000, null, null, cookie, false);
		return httpResult;
	}
	
	public static HttpResult doPost(String url) {
		HttpResult httpResult = httpConntect(url, "POST", "", null, 2000, null, null, "", false);
		return httpResult;
	}
	
	public static HttpResult doPost(String url, Map<String, String> params) {
		
		HttpResult httpResult = httpConntect(url, "POST", JsonUtils.serializeObject(params), null, 2000, null, null, null, false);
		return httpResult;
	}
	
	
	public static HttpResult doPost(String url, Map<String, String> params, String cookie) {
		
		HttpResult httpResult = httpConntect(url, "POST", JsonUtils.serializeObject(params), null, 2000, null, null, cookie, false);
		return httpResult;
	}
	
	public static HttpResult doPost(String url, Map<String, String> params, Map<String, String> cookie) {
		
		HttpResult httpResult = httpConntect(url, "POST", JsonUtils.serializeObject(params), null, 2000, null, null, covertCookie(cookie), false);
		return httpResult;
	}
	
	public static HttpResult doPostOnly(String url, Map<String, String> header, Map<String, String> cookie) {
		
		HttpResult httpResult = httpConntect(url, "POST", null, null, 2000, header, null, covertCookie(cookie), false);
		return httpResult;
	}
	public static HttpResult doPost(String url, Map<String, String> params, Map<String, String> header, String cookie) {
		
		HttpResult httpResult = httpConntect(url, "POST", JsonUtils.serializeObject(params), null, 2000, header, null, cookie, false);
		return httpResult;
	}
	
	public static HttpResult doPost(String url, Map<String, String> params, Map<String, String> header, Map<String, String> cookie) {
		
		HttpResult httpResult = httpConntect(url, "POST", JsonUtils.serializeObject(params), null, 2000, header, null, covertCookie(cookie), false);
		return httpResult;
	}
	public static HttpResult doPost(String url, String params, Map<String, String> header, String cookie) {
		
		HttpResult httpResult = httpConntect(url, "POST", params, null, 2000, header, _Proxy, cookie, false);
		return httpResult;
	}
	
	static ByteArrayOutputStream decompressToStream(InputStream inputStream) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {
			GZIPInputStream ungzip = new GZIPInputStream(inputStream);
			byte[] buffer = new byte[256];
			int n;
			while ((n = ungzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
		} catch (IOException e) {
			throw new RuntimeException("decompress gzip error", e);
		}
		
		return out;
	}
	
	static String covertCookie(Map<String, String> cookieMap) {
		if (cookieMap == null)
			return "";
		String cookieStr = String.join(";", cookieMap.values());
		return cookieStr;
	}
	
	static Map<String, String> getCookie(HttpURLConnection connection) {
		Map<String, String> cookieMap = new HashMap<>();
		Map<String, List<String>> map = connection.getHeaderFields();
		if (map == null || !map.containsKey("Set-Cookie")) {
			return cookieMap;
		}
		List<String> cookies = map.get("Set-Cookie");
		for (String cookieStr : cookies) {
			String[] infoArr = cookieStr.split(";");
			String[] cookieArr = infoArr[0].split("=");
			cookieMap.put(cookieArr[0], cookieStr);
		}
		return cookieMap;
	}
	
	public static Map getDefalutHeader() {
		Map<String, String> mapHeader = new HashMap<>();
		mapHeader.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.26 Safari/537.36 Core/1.63.6788.400 QQBrowser/10.3.2864.400");
		return mapHeader;
	}
}

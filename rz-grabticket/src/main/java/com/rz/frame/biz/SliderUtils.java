package com.rz.frame.biz;

import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;
import com.rz.frame.utils.JsonUtils;
import com.rz.frame.utils.RzLogger;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SliderUtils {
	public static void main(String[] args) {
		try {
			
			//			String jsResutl = getJsResutl("");
			List<BasicHeader> headerList = new ArrayList<>();
			BasicHeader userAgent = new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36");
			headerList.add(userAgent);
			CloseableHttpClient httpClient = HttpClients.custom().setDefaultHeaders(headerList).build();
			
			HttpPost umPost = new HttpPost("https://ynuf.aliapp.org/service/um.json");
			
			List<NameValuePair> umParas = new ArrayList<>();
			
			//定值
			umParas.add(new BasicNameValuePair("data", "106!HWnmc0cl/R6ckgR3jmHhBl6nVz3LHiM7J/dTx8UUzEH0nc6rU2umzIFp8+IZFdAmnlmYZ/pa2bXbzvIlGPv9fc2Zuy4zkqO+T3mjvM9Vz4aybkgPKK1tUJUHmP6h5BKoJjUFFgZxnabhxSQj8UnQZWdX5mE9yQiUmwAhiCMgckm1O4rkys/JPfogzRFPs7OZXWkmhvsko4P1l5Wd2TYJSfWdzGGwE0v042Uj9y/+46HBFgn0S+eU3nHGo2bG/2kU5FUm6T/O42kumt1H7vT0MWraX2AoaQ1jqSGKhNPQh4Uhf5vaTEBq9vbUerglfT0X/7EcwNmf9dMCHoGFJdyC/aJPKuQAYiXICn17VDFH8Fas3uKU9tkP4vzmQnyGXzgqfdYi2BXYDoLBWZbW4+rQiowgS9Rlfgbtg5vjywsY72wjMZ7Lgl0sKKmtN/eu+peG2Pnd74Li/hpDhnvfsoaOqfb47YRP8qj+PZqktsrmG6Kic11BMKpEyosOd+Z5d4TBT3lafc+exL6lBt2/UptBCJpCItih5B0MDqVBqURdKsp4zIb6cW/F0D0m5/sqlsVp97PWG9henUPOp/EF70zRtnjIqbWPVD/PsUTe8Q0Is1Uxawda77WwXLMZ1kHcTjZb7c/RVczGrfVbhScaG1wf2B9mEEca17XMhr+7bGd4lEcIJ8Ybhwn="));//
			umParas.add(new BasicNameValuePair("xa", "FFFF0N000000000085DE"));
			umParas.add(new BasicNameValuePair("xt", ""));
			umParas.add(new BasicNameValuePair("efy", "1"));
			UrlEncodedFormEntity umFormEntity = new UrlEncodedFormEntity(umParas, "UTF-8");
			umPost.setEntity(umFormEntity);
			CloseableHttpResponse umResult = httpClient.execute(umPost);
			String umStr = entityToString(umResult.getEntity());
			JSONObject umObj = JsonUtils.toBean(umStr);
			RzLogger.info("umStr", umStr);
			
			String getUrl = "https://cf.aliyun.com/nocaptcha/initialize.jsonp?a=FFFF0N000000000085DE&t=FFFF0N000000000085DE%3A" + System.currentTimeMillis() + "%3A" + Math.random() + "&scene=nc_login&lang=cn&v=v1.2.17&href=file%3A%2F%2F%2FC%3A%2FUsers%2Ffuwei%2FDesktop%2Ftest.htm&comm=%7B%7D&callback=initializeJsonp_" + String.valueOf(Math.random()).replace(".", "");
			String queryUrl = "https://v2.auc.avira.com/api/query?session=UgveY0RrZXOD0u_XsbrT";
			
			String ca="{\"urls\": [\"https://cf.aliyun.com/nocaptcha/initialize.jsonp?a=FFFF0N000000000085DE&callback=initializeJsonp_0482619996194805&comm={}&href=file%3A%2F%2F%2FC%3A%2FUsers%2Ffuwei%2FDesktop%2FDesktop%2Ftest.htm&lang=cn&scene=nc_login&t=FFFF0N000000000085DE%3A1577205592060%3A0.8767253970699&v=v1.2.17\"], \"wlv\": 142, \"exv\": 109, \"metadata\": {\"res_type\": \"referencedScript\", \"top_frame_url\": \"file:///C:/Users/fuwei/Desktop/Desktop/test.htm\"} }";
			HttpGet initialize = new HttpGet("https://cf.aliyun.com/nocaptcha/initialize.jsonp?a=FFFF0N000000000085DE&t=FFFF0N000000000085DE%3A" + System.currentTimeMillis() + "%3A" + Math.random() + "&scene=nc_login&lang=cn&v=v1.2.17&href=file%3A%2F%2F%2FC%3A%2FUsers%2Ffuwei%2FDesktop%2Ftest.htm&comm=%7B%7D&callback=initializeJsonp_" + String.valueOf(Math.random()).replace(".", ""));
			
			CloseableHttpResponse initializeResult = httpClient.execute(initialize);
			String initializeStr = entityToString(initializeResult.getEntity());
			RzLogger.info("initialize", initializeStr);
			
			
			WebClient webClient = new WebClient(BrowserVersion.CHROME);
			webClient.getOptions().setJavaScriptEnabled(true);              // 启用JS解释器，默认为true
			webClient.getOptions().setCssEnabled(false);                    // 禁用css支持
			webClient.getOptions().setThrowExceptionOnScriptError(false);   // js运行错误时，是否抛出异常
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setTimeout(10 * 1000);                   // 设置连接超时时间
			HtmlPage page = webClient.getPage("http://127.0.0.1:8080/examples/huakuai01.html");
			webClient.waitForBackgroundJavaScript(2 * 1000);
			String n = page.getElementsByTagName("div").get(0).asText();
			//			HttpGet guiji = new HttpGet("http://127.0.0.1:8080/examples/huakuai01.html");
			//			CloseableHttpResponse guijiResult = httpClient.execute(guiji);
			//			String guijiStr = entityToString(guijiResult.getEntity());
			//			RzLogger.info("guiji", guijiStr);
			RzLogger.info("获得轨迹：", n);
			String analyzUrl = "https://cf.aliyun.com/nocaptcha/analyze.jsonp?";
			String para = "FFFF0N000000000085DE&t=FFFF0N000000000085DE:" + System.currentTimeMillis() + ":0" + String.valueOf(Math.random()).replace(".", "") + "&n=%s&p=%s&scene=nc_login&asyn=0&lang=cn&v=993&callback=jsonp_" + String.valueOf(Math.random()).replace(".", "");
			HashMap<String, String> p = new HashMap<>();
			p.put("key1", "code0");
			p.put("ncSessionID", "1a6b9156c");
			p.put("umidToken", umObj.getString("tn"));
			//			String n = "122#cDKMcEoYEEJORDpZy4pIEJponDJE7SNEEP7ZpJRBuDPpJFQLpCGwoHZDpJEL7SwBEy7HGJ/RVEEP+oQLpoGUEELWn4yP7SQEpyGZpJRBuDPE+BNPpC76EJponDJLKMQEI4v8XDJ2tO9/a2Ie3P7NP2wX+U50qJw7tMVhwFYlfWrIx0gjWpPt5q7ohoLwVE+Dqgf2C4XxnSp4hyaEDtVZ8CL6JDEEyBfDqWv2EEpadBZ9kxgbELVrtFp6+/jPYx+m7Mt2E5pangL4ul0EDLVr8C+UJDEEyBfDqMfbDEpxnSp1uOIEEIhr8Cpka4bEyBfG7g/qEWfAAWJElOa2KDVb7aIIGRkP+y7lh454zlKbaTAiEdw6aEy2ZPMnQrajTHHN19261HctyBEHB8KpinINU6sVIxcmHOHkzkgSPuqIEI4S0wnqWDeR0L8yGh9xAOWMOrDBdp5NywQ2Lj37NDMQ4WJhhQ2kgNuTtWe+Ki/p6ODdT+70/cQhfd28IJnphCYMns7mzIO5kzOxWwkU5uGqkqa15E5O35n07cefqMvQXtz4vfxjckWG7DEyktP1WJHnJDafyZHYE35daHvcbufbGPC7mn/Hou/I6/Ddx4h8LOmMjailLUBccxd/f1LgMMPefBQFEdxfB24T4carDPXzW8SY2MKmlrAfz5mui7EAzJGq4y1fQLEq8D==";
			HttpGet analyzGet = new HttpGet(analyzUrl + URLEncoder.encode(String.format(para, n, JsonUtils.serializeObject(p))));
			CloseableHttpResponse analyzGetResult = httpClient.execute(analyzGet);
			String analyzGetStr = entityToString(analyzGetResult.getEntity());
			RzLogger.info("analyzGet", analyzGetStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static String getJsResutl(String jsPath) {
		//js function：getRouteInfo，入参为province
		String routeScript = "function getRouteInfo(){ return \"test\"}";
		
		String scriptResult = "";//脚本的执行结果
		
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");//1.得到脚本引擎
		try {
			//2.引擎读取 脚本字符串
			//			engine.eval(new StringReader(routeScript));
			//如果js存在文件里，举例
			Resource aesJs = new ClassPathResource("js/aes.js");
			engine.eval(new FileReader(aesJs.getFile()));
			
			//3.将引擎转换为Invocable，这样才可以掉用js的方法
			Invocable invocable = (Invocable) engine;
			
			//4.使用 invocable.invokeFunction掉用js脚本里的方法，第一個参数为方法名，后面的参数为被调用的js方法的入参
			scriptResult = (String) invocable.invokeFunction("getRouteInfo", "henan");
			
		} catch (ScriptException e) {
			e.printStackTrace();
			System.out.println("Error executing script: " + e.getMessage() + " script:[" + routeScript + "]");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			System.out.println("Error executing script,为找到需要的方法: " + e.getMessage() + " script:[" + routeScript + "]");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return scriptResult;
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
}

package com.rz.frame.core;


import com.rz.frame.entity.Constant;
import com.rz.frame.entity.MetaInfo;
import com.rz.frame.utils.JsonUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

@RestController
public class RzController {
	@RequestMapping(value = "/api/apiList")
	public String apiList() {
		StringBuilder apiContent = new StringBuilder();
		Set<Map.Entry<String, MetaInfo>> entrys = Container.getEntrys();
		for (Map.Entry<String, MetaInfo> map : entrys) {
			if (map.getKey().equals(Constant.SERVICE_NAME)) {
				apiContent.append(String.format("<h2>%s</h2>", map.getValue().getMethodName()));
				continue;
			}
			apiContent.append(String.format("<h3>%s</h3>", map.getValue().getMethodName()));
		}
		return apiContent.toString();
	}
	
	@RequestMapping(value = "/api/execApi/{apiName}", method = RequestMethod.POST)
	public String execApi(@PathVariable("apiName") String apiName, @RequestBody String request) {
		try {
			MetaInfo metaInfo = Container.get(Constant.SERVICE_NAME);
			Class<?> serviceClass = metaInfo.getRequestClass();
			MetaInfo methodMeta = Container.get(apiName);
			Method method = serviceClass.getDeclaredMethod(apiName, methodMeta.getRequestClass());
			Object rq = JsonUtils.toBean(request, methodMeta.getRequestClass());
			Object invoke = method.invoke(serviceClass.newInstance(), rq);
			return JsonUtils.toJSON(invoke);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
		
	}
}

package com.rz.frame.core;

import com.rz.frame.entity.Constant;
import com.rz.frame.entity.MetaInfo;

import java.lang.reflect.Method;

public class RegisterSerivce {
	
	public static void registerService(Class serviceClazz) {
		
		MetaInfo metaInfo = new MetaInfo();
		metaInfo.setMethodName(serviceClazz.getSimpleName());
		metaInfo.setRequestClass(serviceClazz);
		Container.set(Constant.SERVICE_NAME, metaInfo);
		Method[] methods = serviceClazz.getDeclaredMethods();
		if (methods == null) {
			return;
		}
		for (Method method : methods) {
			MetaInfo mInfo = new MetaInfo();
			mInfo.setMethodName(method.getName());
			Class<?>[] parameterTypes = method.getParameterTypes();
			mInfo.setRequestClass(parameterTypes[0]);
			mInfo.setResponseClass(method.getReturnType());
			Container.set(method.getName(), mInfo);
		}
		
		
	}
}

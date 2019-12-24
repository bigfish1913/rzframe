package com.rz.frame.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.*;


public class JsonUtils {
	private static final SerializeConfig config;
	
	static {
		config = new SerializeConfig();
		config.put(Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
		config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
	}
	
	
	private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
			SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
			SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
			SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
			SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
	};
	
	/**
	 * json序列化
	 *
	 * @param o
	 * @return
	 */
	public static String serializeObject(Object o) {
		return JSON.toJSONString(o, config, features);
		
	}
	
	public static String toJSON(Object o) {
		return JSON.toJSONString(o, config, features);
		
	}
	
	public static JSON toJSONObject(Object o) {
		return (JSON) JSON.toJSON(o);
		
	}
	
	public static String serializeObject(Object o, String... ignores) {
		if (ignores.length > 0) {
			PropertyFilter profilter = (object, name, value) -> {
				if (Arrays.asList(ignores).contains(name)) {
					//false表示last字段将被排除在外
					return false;
				}
				return true;
			};
			return JSON.toJSONString(o, config, profilter, features);
		}
		return serializeObject(o);
	}
	
	/**
	 * json序列化
	 *
	 * @param o
	 * @return
	 */
	public static String serializeObjectExceptNull(Object o) {
		return JSON.toJSONString(o, config);
		
	}
	
	/**
	 * json反序列化
	 *
	 * @param jsonBody
	 * @return
	 */
	public static JSONObject toBean(String jsonBody) {
		return (JSONObject)JSON.parse(jsonBody);
	}
	
	/**
	 * @param
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T toBean(String jsonBody, Class<T> clazz) {
		return JSON.parseObject(jsonBody, clazz);
	}
	
	public static <T> List<T> toArray(String jsonBody, Class<T> clazz) {
		return JSON.parseArray(jsonBody, clazz);
	}
 
	
	
	
	
	
}

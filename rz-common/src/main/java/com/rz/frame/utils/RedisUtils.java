package com.rz.frame.utils;

import redis.clients.jedis.Jedis;
import sun.swing.StringUIClientPropertyKey;

import java.util.List;

public class RedisUtils {
	static Jedis jedis = new Jedis("192.168.0.108");
	
	
	public static <T> T get(String key, Class<T> clazz) {
		try {
			String content = jedis.get(key);
			if (StringUtils.isEmpty(content)) {
				return null;
			}
			return JsonUtils.toBean(content, clazz);
		} catch (Exception ex) {
			return null;
		}
	}
	
	public static String get(String key) {
		try {
			String content = jedis.get(key);
			if (StringUtils.isEmpty(content)) {
				return null;
			}
			return content;
		} catch (Exception ex) {
			return null;
		}
	}
	public static <T> List<T> getList(String key, Class<T> clazz) {
		try {
			String content = jedis.get(key);
			if (StringUtils.isEmpty(content)) {
				return null;
			}
			return JsonUtils.toArray(content, clazz);
		} catch (Exception ex) {
			return null;
		}
	}
	
	public static void set(String key, Object o) {
		try {
			if (o == null)
				return;
			String content = jedis.set(key, JsonUtils.serializeObject(o));
			return;
		} catch (Exception ex) {
			return;
		}
	}
	
	public static void set(String key, Object o, int senconds) {
		try {
			if (o == null)
				return;
			String content = jedis.setex(key, senconds,JsonUtils.serializeObject(o));
			return;
		} catch (Exception ex) {
			return;
		}
	}
	
	public static long incr(String key) {
		try {
			return jedis.incr(key);
		} catch (Exception ex) {
			return 0;
		}
	}
}

package com.rz.frame.core;


import com.rz.frame.entity.MetaInfo;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Container {
	private final static ConcurrentHashMap<String, MetaInfo> container = new ConcurrentHashMap<String, MetaInfo>();
	public static void set(String key, MetaInfo metaInfo) {
		container.put(key, metaInfo);
	}
	
	public static MetaInfo get(String key) {
		return container.get(key);
	}
	
	public static Set<Map.Entry<String, MetaInfo>> getEntrys() {
		return container.entrySet();
	}
}

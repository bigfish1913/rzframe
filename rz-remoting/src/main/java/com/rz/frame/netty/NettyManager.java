package com.rz.frame.netty;

import com.rz.frame.utils.RzLogger;

import java.util.concurrent.ConcurrentHashMap;

public class NettyManager {
	final static ConcurrentHashMap<String, NettyClient> NettyClientContainer = new ConcurrentHashMap<>();
	private static NettyManager nettyManager;
	
	//单例可以换成Spring
	public static NettyManager getInstance() {
		if (nettyManager == null) {
			nettyManager = new NettyManager();
		}
		return nettyManager;
	}
	
	public NettyClient getNettyClient(String ip, int port) {
		String ipAndPort = ip.concat(":").concat(String.valueOf(port));
		if (NettyClientContainer.containsKey(ipAndPort)) {
			NettyClient nettyClient = NettyClientContainer.get(ipAndPort);
			if (!nettyClient.getConnected().get()) {
				nettyClient.connect();
			}
			return nettyClient;
		}
		NettyClient newNettyClient = null;
		try {
			newNettyClient = new NettyClient(ip, port);
			newNettyClient.connect().waitConnect();
			NettyClientContainer.put(ipAndPort, newNettyClient);
		} catch (Exception e) {
			RzLogger.error("创建NettyClient失败");
		}
		return newNettyClient;
	}
}

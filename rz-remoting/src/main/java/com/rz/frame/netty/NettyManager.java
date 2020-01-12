package com.rz.frame.netty;

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
		String ipAndPort = ip.concat(String.valueOf(port));
		if (NettyClientContainer.containsKey(ipAndPort)) {
			NettyClient nettyClient = NettyClientContainer.get(ipAndPort);
			if (!nettyClient.getConnected().get()) {
				nettyClient.connect();
			}
			return nettyClient;
		}
		NettyClient newNettyClient = new NettyClient(ip, port);
		try {
			newNettyClient.connect().waitConnect();
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 NettyClientContainer.put(ipAndPort, newNettyClient);
		return newNettyClient;
	}
}

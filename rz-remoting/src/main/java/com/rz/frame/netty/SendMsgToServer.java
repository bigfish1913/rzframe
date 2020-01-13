package com.rz.frame.netty;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SendMsgToServer {
	private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	
	public static void main(String[] args) {
		String ip = "127.0.0.1";
		int port = 9098;
		NettyClient nettyClient = NettyManager.getInstance().getNettyClient(ip, port);
		try {
//			nettyClient.reconnect();
////
			executor.scheduleAtFixedRate(()-> {
				try {
					nettyClient.sendHeartBeatRequest();
				} catch (Exception e) {
					e.printStackTrace();
				}
			},1,1, TimeUnit.SECONDS);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

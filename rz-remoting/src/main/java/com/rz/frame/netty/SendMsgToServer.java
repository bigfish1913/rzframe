package com.rz.frame.netty;

public class SendMsgToServer {
	public static void main(String[] args) {
		String ip = "127.0.0.1";
		int port = 9098;
		NettyClient nettyClient = NettyManager.getInstance().getNettyClient(ip, port);
		try {
			for (int i = 0; i < 100; i++) {
				nettyClient.sendMessage("开始测试消息发送:"+i);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

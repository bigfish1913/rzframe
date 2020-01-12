package com.rz.frame.netty;

import com.rz.frame.utils.RzLogger;

import java.util.concurrent.CountDownLatch;

public class ClientBootStrap {
	public static void main(String[] args) {
		CountDownLatch countDownLatch=new CountDownLatch(1);
		NettyClient nettyClient = new NettyClient(HeartConstant.Address.IP, HeartConstant.Address.PORT,countDownLatch);
		Thread thread=new Thread(nettyClient::connect);
		thread.start();
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		RzLogger.info("客户端启动成功");
	}
}

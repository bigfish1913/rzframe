package com.rz.frame.netty;

import com.rz.frame.utils.RzLogger;

import java.util.concurrent.CountDownLatch;

public class ServerBootStrap {
	public static void main(String[] args) {
		CountDownLatch countDownLatch=new CountDownLatch(1);
		NettyServer nettyServer = new NettyServer("127.0.0.1",9098,countDownLatch);
		Thread thread=new Thread(nettyServer::startServer);
		thread.start();
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		RzLogger.info("服务端启动成功");
	}
}

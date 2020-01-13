package com.rz.frame.netty;


import com.rz.frame.netty.handler.ClientHandler;
import com.rz.frame.utils.RzLogger;
import com.sun.jmx.remote.util.ClassLogger;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.rz.frame.netty.HeartConstant.Config.READ_TIME_OUT;
import static com.rz.frame.netty.HeartConstant.Config.WRITE_TIME_OUT;
import static com.rz.frame.netty.HeartConstant.RemotingHeader.HeartMessage;


public class NettyClient {
	private Channel channel;
	private final String IP;
	private final int Port;
	private CountDownLatch countDownLatch;
	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	/*连接是否成功关闭的标志值*/
	private AtomicBoolean connected = new AtomicBoolean(false);
	EventLoopGroup group = new NioEventLoopGroup();
	
	public NettyClient(String IP, int port, CountDownLatch countDownLatch) {
		this.IP = IP;
		Port = port;
		this.countDownLatch = countDownLatch;
	}
	
	public NettyClient(String IP, int port) {
		this.IP = IP;
		Port = port;
		this.countDownLatch = new CountDownLatch(1);
	}
	
	public NettyClient connect() {
		
		try {
			RzLogger.info("尝试连接服务器");
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline p = ch.pipeline();
					p.addLast(new RzEncoder());
					p.addLast(new RzDecoder());
					p.addLast(new IdleStateHandler(READ_TIME_OUT, WRITE_TIME_OUT, READ_TIME_OUT + WRITE_TIME_OUT));
					p.addLast(new ClientHandler());
				}
			});
			ChannelFuture future = b.connect(IP, Port).sync();
			channel = future.channel();
			connected.set(true);
			
		} catch (Exception e) {
			RzLogger.error("connect", e.getMessage());
		} finally {
			countDownLatch.countDown();
		}
		return this;
	}
	
	public void reconnect() {
		
		while (!connected.get())
		{
			RzLogger.info("异常断开，尝试重连");
			connect();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void sendMessage(String msg, MessageType messageType, ContentType contentType) throws Exception {
		if (channel == null || !connected.get()) {
			throw new Exception("尚未连接成功");
		}
		Message message = MessageGenerater.generaterMessage(msg, messageType, contentType);
		//		RzLogger.info("发送消息到服务端：" + message);
		channel.writeAndFlush(message);
	}
	
	public void sendMessage(String msg, MessageType messageType) throws Exception {
		sendMessage(msg, messageType, ContentType.Default);
	}
	
	public void sendHeartBeatRequest() throws Exception {
		sendMessage(HeartMessage, MessageType.HEARTBEAT_REQ, ContentType.Default);
	}
	
	public void sendHeartBeatResponse() throws Exception {
		sendMessage(HeartMessage, MessageType.HEARTBEAT_RESP, ContentType.Default);
	}
	
	public AtomicBoolean getConnected() {
		return connected;
	}
	
	public void setConnected(AtomicBoolean connected) {
		this.connected = connected;
	}
	
	public NettyClient waitConnect() throws Exception {
		if (this.countDownLatch == null) {
			throw new Exception("尚未初始化countDownLatch");
		}
		this.countDownLatch.await();
		if (channel == null || !connected.get()) {
			throw new Exception("尚未连接成功");
		}
		return this;
	}
	
	public void close() {
		connected.set(false);
		channel.close();
		group.shutdownGracefully();
	}
}

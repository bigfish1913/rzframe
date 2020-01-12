package com.rz.frame.netty;


import com.rz.frame.netty.handler.ServerHandler;
import com.rz.frame.utils.RzLogger;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.rz.frame.netty.HeartConstant.Config.READ_TIME_OUT;
import static com.rz.frame.netty.HeartConstant.Config.WRITE_TIME_OUT;


public class NettyServer {
	private Channel channel;
	private final String IP;
	private final int Port;
	private final CountDownLatch countDownLatch;
 
	/*连接是否成功关闭的标志值*/
	private AtomicBoolean started = new AtomicBoolean(false);
	
	public NettyServer(String IP, int port, CountDownLatch countDownLatch) {
		this.IP = IP;
		Port = port;
		this.countDownLatch=countDownLatch;
	}
	
	public void startServer() {
		if(started.get()){
			RzLogger.info("服务已经启动");
			return;
		}
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		ServerBootstrap bootStrap = new ServerBootstrap();
		ChannelFuture cf;
		bootStrap.group(bossGroup, workGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 1024)
				.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) {
				ChannelPipeline p = ch.pipeline();
				p.addLast(new RzEncoder());
				p.addLast(new RzDecoder());
				p.addLast(new IdleStateHandler( READ_TIME_OUT,  WRITE_TIME_OUT, READ_TIME_OUT+WRITE_TIME_OUT));
				p.addLast(new ServerHandler());
				
			}
		});
		
		try {
			cf = bootStrap.bind(Port).sync();//监听8099端口
			RzLogger.info("8099:binded...");
			channel = cf.channel();
			started.set(true);
			countDownLatch.countDown();
			channel.closeFuture().sync();
		} catch (InterruptedException e) {
			RzLogger.info("客户端下线");
		} finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
	
	public void sendMessage(Message message) throws Exception {
		if (channel == null || !started.get()) {
			throw new Exception("尚未启动成功");
		}
		
		channel.writeAndFlush(message);
	}
	
}

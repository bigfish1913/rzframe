package com.rz.frame.netty.handler;

import com.rz.frame.netty.*;
import com.rz.frame.utils.RzLogger;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.net.SocketAddress;

@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler<Message> {
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		RzLogger.info("服务器断开");
		SocketAddress socketAddress = ctx.channel().remoteAddress();
		String ipAndPort=socketAddress.toString().replace("/","");
		String ip=ipAndPort.split(":")[0];
		int port=Integer.parseInt(ipAndPort.split(":")[1]);
		NettyClient nettyClient = NettyManager.getInstance().getNettyClient(ip, port);
		nettyClient.close();
		nettyClient.reconnect();
	}
	
	//发现服务端
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		RzLogger.info("服务器连接已激活");
	}
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleState state = ((IdleStateEvent) evt).state();
			RzLogger.info(state.toString());
			if (state.equals(IdleState.READER_IDLE)) {
				RzLogger.info("超时未读取到数据");
			}
			if (state.equals(IdleState.WRITER_IDLE)) {
				RzLogger.info("超时未写入数据");
			}
			
		} else {
			super.userEventTriggered(ctx, evt);
		}
		
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) {
	 
		if (message.getHeader().getMessageType().equals(MessageType.HEARTBEAT_RESP)) {
			RzLogger.info("心跳响应：" + message.getStrContent());
		}
		if (message.getHeader().getMessageType().equals(MessageType.SERVICE_REQ)) {
			RzLogger.info("收到服务端信息：" + message.getStrContent());
			message.getHeader().setMessageType(MessageType.SERVICE_REQ);
			channelHandlerContext.writeAndFlush(message);
		}
		if (message.getHeader().getMessageType().equals(MessageType.SERVICE_RESP)) {
			RzLogger.info("确认收到消息");
		}
	}
}

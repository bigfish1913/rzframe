package com.rz.frame.netty.handler;

import com.rz.frame.netty.Message;
import com.rz.frame.netty.MessageGenerater;
import com.rz.frame.netty.MessageType;
import com.rz.frame.utils.RzLogger;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler<Message> {
	
	//发现服务端
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		RzLogger.info("服务器连接已激活");
		Message message = MessageGenerater.getMessage("发现服务端");
		System.out.println("NettyClient|: channelActive 发送消息：" + message.toString());
		ctx.writeAndFlush(message);
		
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
		RzLogger.info("收到服务端信息：" + message.getStrContent());
		if (message.getHeader().getMessageType().equals(MessageType.HEARTBEAT_RESP)) {
			//			Clogger.info("收到心跳返回信息：" + message.toString());
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

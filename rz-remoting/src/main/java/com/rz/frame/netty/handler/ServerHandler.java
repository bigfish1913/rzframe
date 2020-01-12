package com.rz.frame.netty.handler;

import com.rz.frame.netty.Message;
import com.rz.frame.netty.MessageGenerater;
import com.rz.frame.netty.MessageHead;
import com.rz.frame.netty.MessageType;
import com.rz.frame.utils.RzLogger;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class ServerHandler extends SimpleChannelInboundHandler<Message> {
	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) {
		RzLogger.info("收到客户端消息：" + message.getStrContent());
		
		if (message.getHeader().getMessageType().equals(MessageType.HEARTBEAT_REQ)) {
			message.getHeader().setMessageType(MessageType.HEARTBEAT_RESP);
			channelHandlerContext.writeAndFlush(message);
		}
		if (message.getHeader().getMessageType().equals(MessageType.SERVICE_RESP)) {
			RzLogger.info("确认收到消息");
		}
		
		if (message.getHeader().getMessageType().equals(MessageType.SERVICE_REQ)) {
			RzLogger.info("收到服务端信息：" + message.getStrContent());
			message.getHeader().setMessageType(MessageType.SERVICE_RESP);
			channelHandlerContext.writeAndFlush(message);
		}
		
		
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		RzLogger.info("客户端：" + ctx.channel().remoteAddress() + "连接成功！");
		Message message = MessageGenerater.getMessage("发送测试消息");
		ctx.writeAndFlush(message);
		ctx.fireChannelActive();
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
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		RzLogger.info("客户端：" + ctx.channel().remoteAddress() + "已经断开");
	}
}


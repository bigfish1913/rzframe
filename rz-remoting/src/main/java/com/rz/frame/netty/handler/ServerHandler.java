package com.rz.frame.netty.handler;

import com.rz.frame.netty.*;
import com.rz.frame.utils.RzLogger;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import static com.rz.frame.netty.HeartConstant.RemotingHeader.HeartMessage;

public class ServerHandler extends SimpleChannelInboundHandler<Message> {
	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) {
		
		if (message.getHeader().getMessageType().equals(MessageType.HEARTBEAT_REQ)) {
			RzLogger.info("收到心跳请求：" + message.getStrContent());
			Message heartMessage = MessageGenerater.generaterHeartMessage(HeartMessage, MessageType.HEARTBEAT_RESP);
			channelHandlerContext.writeAndFlush(heartMessage);
		}
		if (message.getHeader().getMessageType().equals(MessageType.SERVICE_REQ)) {
			if (message.getHeader().getContentType().equals(ContentType.File)) {
				FileUtils.saveFile(message.getContent(), "D:\\1.png");
			}
		}
		
//		if (message.getHeader().getMessageType().equals(MessageType.SERVICE_REQ)) {
//			RzLogger.info("收到服务端信息：" + message.getStrContent());
//			message.getHeader().setMessageType(MessageType.SERVICE_RESP);
//			channelHandlerContext.writeAndFlush(message);
//		}
		
		
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		RzLogger.info("客户端：" + ctx.channel().remoteAddress() + "连接成功！");
		Message message = MessageGenerater.generaterMessage("发送测试消息");
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


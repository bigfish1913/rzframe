package com.rz.frame.entityChat;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@ChannelHandler.Sharable
public class ServerMessageHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Server|:channelRegistered 服务已启动,等待客户连接");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Server|:channelActive 服务连接成功");
        Message message = MessageGenerater.getMessage("channelActive 服务连接成功");
        ctx.writeAndFlush(message);
    }


    //接收客户端发送的消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message invokeMsg = (Message) msg;
        System.out.println("Server|: channelRead收到客户端消息：" + invokeMsg.toString());
        Message message = MessageGenerater.getMessage("给客户端回复消息");
        System.out.println("Server|: channelRead给客户端回复消息：" + message.toString());
        ctx.write(message);//给客户端回复
        ctx.flush();
    }
}
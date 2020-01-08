package com.rz.frame.entityChat;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@ChannelHandler.Sharable
public class ClientMessageHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("NettyClient|: channelActive 发现服务端");
        Message message = MessageGenerater.getMessage("发现服务端");
        System.out.println("NettyClient|: channelActive 发送消息：" + message.toString());
        ctx.writeAndFlush(message);
        ctx.fireChannelActive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message res = (Message) msg;
        System.out.println("NettyClient|: channelRead 收到服务端消息: " + res.toString());
        Thread.sleep(10);
        Message message = MessageGenerater.getMessage("再次回复服务端");
        System.out.println("NettyClient|: channelRead 再次回复服务端：" + message.toString());
        ctx.writeAndFlush(message);

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("NettyClient|: channelReadComplete");
        cause.printStackTrace();
    }

}
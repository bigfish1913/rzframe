package com.rz.frame.utils.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;
import jdk.nashorn.internal.runtime.linker.Bootstrap;
import org.apache.commons.lang.CharUtils;

import java.net.InetSocketAddress;

public class NettyCient {
    public void  run(int port) throws InterruptedException {
        EventLoopGroup group=new NioEventLoopGroup();
        try {
            ServerBootstrap b=new ServerBootstrap();
//            b.group(group).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BROADCAST).handler(new ClientHandler());
            Channel channel=b.bind(0).sync().channel();

            channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("谚语字典查询？",CharsetUtil.UTF_8),new InetSocketAddress("255.255.255.255",port))).sync();

            if(!channel.closeFuture().await(15000)){
                System.out.println("查询超时！");
            }



        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
//        new NettyCient().run(8756);
//        new NettyCient().run();
    }
}


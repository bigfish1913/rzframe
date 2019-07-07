package com.rz.frame.utils.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import jdk.nashorn.internal.runtime.linker.Bootstrap;

public class NettyServer {

    public void run(int port) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
//            b.group(group).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BROADCAST).handler(new DiscardServerHandler());
            b.bind(port).sync().channel().closeFuture().await();

        } finally {
            group.shutdownGracefully();

        }
    }

    public static void main(String[] args) throws Exception {
        new NettyServer().run(8764);
        new NettyServer().run(8764);
    }
}

package com.rz.frame.utils.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class NettyUtils {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup pGroup=new NioEventLoopGroup();
        EventLoopGroup cGroup=new NioEventLoopGroup();

        ServerBootstrap bootstrap=new ServerBootstrap();
        bootstrap.group(pGroup,cGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .option(ChannelOption.SO_SNDBUF,32*1024)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(new DiscardServerHandler());
                    }

                });
        ChannelFuture cf=  bootstrap.bind(8756).sync();
        cf.channel().closeFuture().sync();
        pGroup.shutdownGracefully();
        cGroup.shutdownGracefully();
    }

}

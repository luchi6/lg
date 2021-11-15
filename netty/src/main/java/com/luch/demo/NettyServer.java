package com.luch.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author luch
 * @date 2021/11/4 0:25
 */


public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //1、创建bossGroup线程组：处理网络事件--连接事件
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //2、创建workGroup线程组：处理网络事件--读写事件 默认2*处理器线程数
        EventLoopGroup workGroup = new NioEventLoopGroup();
        //3、创建服务端启动助手
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //4、启动助手绑定并设置bossGroup和workGroup
        serverBootstrap.group(bossGroup, workGroup)
                //5、设置服务端通道实现为NIO
                .channel(NioServerSocketChannel.class)
                //6、参数设置
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                // 7、创建一个通道初始化对象
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        // 8、向pipeline中添加自定义业务处理handler
                        channel.pipeline().addLast(new NettyServerHandler());
                    }
                });
        // 9、启动服务端并绑定端口，同时将异步改为同步
        ChannelFuture future = serverBootstrap.bind(9999);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    System.out.println("端口绑定成功");
                } else {
                    System.out.println("端口绑定失败");
                }
            }
        });
        System.out.println("服务端启动成功");
        // 10、关闭通道(并不不是真正意义的关闭，而是监听通道关闭的状态**)和关闭连接池
        future.channel().closeFuture().sync();
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();



    }
}

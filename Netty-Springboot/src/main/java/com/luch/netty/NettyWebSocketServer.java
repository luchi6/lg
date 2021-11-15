package com.luch.netty;

import com.luch.config.NettyConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author luch
 * @date 2021/11/13 21:16
 */

@Component
public class NettyWebSocketServer implements Runnable {
    private EventLoopGroup bossGroup = new NioEventLoopGroup(1);

    private EventLoopGroup workGroup = new NioEventLoopGroup();

    @Autowired
    private NettyConfig nettyConfig;

    @Autowired
    private WebSocketChannelInit webSocketChannelInit;

    @PreDestroy
    public void close() {
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }

    @Override
    public void run() {
        try {
            // 1.创建服务端启动助手
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 2.设置线程组
            serverBootstrap.group(bossGroup, workGroup);
            // 3.设置参数
            serverBootstrap.channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(webSocketChannelInit);
            // 4.启动
            ChannelFuture channelFuture = serverBootstrap.bind(nettyConfig.getPort()).sync();
            System.out.println("========netty服务端启动成功=========");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}

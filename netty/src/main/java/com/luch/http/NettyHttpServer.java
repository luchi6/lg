package com.luch.http;

import com.luch.chat.NettyChatServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author luch
 * @date 2021/11/5 0:49
 */


public class NettyHttpServer {
    private int port;

    public NettyHttpServer(int port) {
        this.port = port;
    }

    public  void run()  {
        //1、创建bossGroup线程组：处理网络事件--连接事件
        EventLoopGroup bossGroup = null;
        //2、创建workGroup线程组：处理网络事件--读写事件 默认2*处理器线程数
        EventLoopGroup workGroup = null;
        try {
            bossGroup = new NioEventLoopGroup(1);
            workGroup = new NioEventLoopGroup();
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
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 添加编解码器
                            socketChannel.pipeline().addLast(new HttpServerCodec());
                            // 8、向pipeline中添加自定义业务处理handler
                            socketChannel.pipeline().addLast(new NettyHttpServerHandler());

                        }
                    });
            // 9、启动服务端并绑定端口，同时将异步改为同步
            ChannelFuture future = serverBootstrap.bind(port);
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
            System.out.println("http服务端启动成功");
            // 10、关闭通道(并不不是真正意义的关闭，而是监听通道关闭的状态**)和关闭连接池
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (bossGroup != null) {
                bossGroup.shutdownGracefully();
            }
            if (workGroup != null) {
                workGroup.shutdownGracefully();
            }
        }
    }

    public static void main(String[] args) {
        new NettyHttpServer(8080).run();
    }
}

package com.luch.chat;

import com.luch.codec.MessageCodec;
import com.luch.codec.NettyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author luch
 * @date 2021/11/8 21:43
 */


public class NettyChatClient {
    private String ip;
    private int port;

    public NettyChatClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void run() throws InterruptedException {
        NioEventLoopGroup group = null;
        try {
            group = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 添加编解码器
                            socketChannel.pipeline().addLast(new StringEncoder());
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new NettyChatClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect(ip, port).sync();
            Channel channel = future.channel();
            System.out.println("================" + channel.localAddress().toString().substring(1) + "=================");

            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                String msg = sc.nextLine();
                channel.writeAndFlush(msg);
            }
            channel.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NettyChatClient nettyChatClient = new NettyChatClient("127.0.0.1", 9998);
        nettyChatClient.run();
    }
}

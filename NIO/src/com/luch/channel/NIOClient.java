package com.luch.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author luch
 * @date 2021/11/3 21:34
 */


public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));
        socketChannel.write(ByteBuffer.wrap("我是客户端".getBytes()));
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        int read = socketChannel.read(allocate);
        System.out.println("服务端消息：" + new String(allocate.array(),0, read, StandardCharsets.UTF_8));
        socketChannel.close();
    }
}

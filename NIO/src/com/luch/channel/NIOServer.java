package com.luch.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author luch
 * @date 2021/11/3 21:22
 */


public class NIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        System.out.println("服务端启动成功");
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel == null) {
                continue;
            }
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            //正数：读取到有效字节数
            //0：没有读取到数据
            //-1：读取到数据末尾
            int read = socketChannel.read(allocate);
            System.out.println("客户端消息：" + new String(allocate.array(), 0, read, StandardCharsets.UTF_8));
            socketChannel.write(ByteBuffer.wrap("我是服务器".getBytes()));
            socketChannel.close();

        }
    }
}

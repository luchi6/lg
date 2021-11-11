package com.luch.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author luch
 * @date 2021/10/25 22:15
 */


public class ServerDemo {
    public static void main(String[] args) throws Exception {
//        1、创建一个线程池，如果有客户端连接就创建一个线程，与之通信
        ExecutorService executorService = Executors.newCachedThreadPool();
        //2、创建ServerSocket对象
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务已启动");
        while (true) {
            //3、监听客户端
            Socket socket = serverSocket.accept();
            System.out.println("有客户端连接");
            //4、开启新的线程处理
            executorService.execute(() -> handle(socket));
        }

    }

    private static void handle(Socket socket) {
        try (InputStream is = socket.getInputStream();
             OutputStream os = socket.getOutputStream();
        ) {
            System.out.println("线程ID:" + Thread.currentThread().getId()
                    + "    线程名称：" + Thread.currentThread().getName());
            //从连接中取出输入流接收消息

            byte[] bytes = new byte[1024];
            int read = is.read(bytes);
            System.out.println("客户端：" + new String(bytes, 0, read));

            //连接中取出输出流并回话

            os.write("你好，客户端".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

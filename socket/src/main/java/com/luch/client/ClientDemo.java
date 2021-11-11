package com.luch.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author luch
 * @date 2021/10/25 22:14
 */


public class ClientDemo {

    public static void main(String[] args) throws IOException {
        while (true) {
            //1.创建socket对象
            Socket socket = new Socket("127.0.0.1", 9999);

            //2.从连接中取出输出流发送消息
            OutputStream os = socket.getOutputStream();
            System.out.println("请输入：");
            Scanner sc = new Scanner(System.in);
            String msg = sc.nextLine();
            os.write(msg.getBytes());

            //3.从连接中取出输入流接收回话
            InputStream is = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int read = is.read(bytes);
            System.out.println("服务器：" + new String(bytes, 0, read).trim());
            socket.close();
        }
    }
}

package com.luch.buffer;

import java.nio.ByteBuffer;

/**
 * @author luch
 * @date 2021/11/3 0:04
 */


public class CreateBufferDemo {
    public static void main(String[] args) {
        //创建无内容的缓冲区
        int bufferSize = 5;
        ByteBuffer byteBuffer = ByteBuffer.allocate(bufferSize);
        for (int i = 0; i < bufferSize; i++) {
            System.out.println(byteBuffer.get());
        }

        //创建有内容的缓冲区
        String str = "luch";
        byte[] bytes = str.getBytes();
        int len = bytes.length;
        ByteBuffer wrap = ByteBuffer.wrap(bytes);
        for (int i = 0; i < len; i++) {
            System.out.println(wrap.get());
        }
    }
}

package com.luch.buffer;

import java.nio.ByteBuffer;

/**
 * @author luch
 * @date 2021/11/3 0:27
 */


public class PutBufferDemo {
    public static void main(String[] args) {
        ByteBuffer allocate = ByteBuffer.allocate(10);
        System.out.println(allocate.position());
        System.out.println(allocate.limit());
        System.out.println(allocate.capacity());
        System.out.println(allocate.remaining());

        allocate.put("luch".getBytes());
    }
}

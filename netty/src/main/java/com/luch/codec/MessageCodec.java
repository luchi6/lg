package com.luch.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @author luch
 * @date 2021/11/5 0:32
 */


public class MessageCodec extends MessageToMessageCodec {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, List list) throws Exception {
        System.out.println("消息正在编码...");
        String str = (String) o;
        list.add(Unpooled.copiedBuffer(str, CharsetUtil.UTF_8));
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Object o, List list) throws Exception {
        System.out.println("正在进行解码。。。");
        ByteBuf byteBuf = (ByteBuf) o;
        // 传递到下一个handler
        list.add(byteBuf.toString(CharsetUtil.UTF_8));
    }
}

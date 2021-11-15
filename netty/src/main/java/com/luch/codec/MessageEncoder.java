package com.luch.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @author luch
 * @date 2021/11/5 0:25
 */


public class MessageEncoder extends MessageToMessageEncoder {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, List list) throws Exception {
        System.out.println("消息正在编码...");
        String str = (String) o;
        list.add(Unpooled.copiedBuffer(str, CharsetUtil.UTF_8));

    }
}

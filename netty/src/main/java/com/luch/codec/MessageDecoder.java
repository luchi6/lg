package com.luch.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @author luch
 * @date 2021/11/4 23:39
 */


public class MessageDecoder extends MessageToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Object o, List list) throws Exception {
        System.out.println("正在进行解码。。。");
        ByteBuf byteBuf = (ByteBuf) o;
        // 传递到下一个handler
        list.add(byteBuf.toString(CharsetUtil.UTF_8));
    }
}

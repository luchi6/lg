package com.luch.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luch
 * @date 2021/11/13 21:42
 */
@Component
@ChannelHandler.Sharable
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    public static List<Channel> channelList = new ArrayList<>();

    /**
     * 通道就绪事件（用户在线）
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelList.add(channel);
        System.out.println("有新的连接");
    }

    /**
     * 通道未就绪（用户下线）
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelList.remove(channel);
    }

    /**
     * 读就绪事件
     *
     * @param channelHandlerContext
     * @param textWebSocketFrame
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        String msg = textWebSocketFrame.text();
        System.out.println(msg);
        // 当前发送消息的通道，当前发送的客户端连接
        Channel channel = channelHandlerContext.channel();
        for (Channel channel1 : channelList) {
            // 排除自身通道
            if (channel1 != channel) {
                channel1.writeAndFlush(new TextWebSocketFrame(msg));
            }
        }
    }

    /**
     * 异常处理事件
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        Channel channel = ctx.channel();
        channelList.remove(channel);
    }
}

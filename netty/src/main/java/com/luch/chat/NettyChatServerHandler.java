package com.luch.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luch
 * @date 2021/11/8 0:11
 */


public class NettyChatServerHandler extends SimpleChannelInboundHandler<String> {
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
        System.out.println("[Server]:" +
                channel.remoteAddress().toString().substring(1) + "在线");
    }

    /**
     * 通道未就绪（用户下线）
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelList.remove(channel);
        System.out.println("[Server]:" +
                channel.remoteAddress().toString().substring(1) + "下线");
    }

    /**
     * 通道读取事件
     *
     * @param channelHandlerContext
     * @param s
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        //当前发送消息的通道，当前发送的客户端连接
        Channel channel = channelHandlerContext.channel();
        for (Channel channel1 : channelList) {
            //排除自身通道
            if (channel1 != channel) {
                channel1.writeAndFlush("[" + channel.remoteAddress().toString().substring(1) + "]:" + s);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        Channel channel = ctx.channel();
        channelList.remove(channel);
        System.out.println("[Server]:" +
                channel.remoteAddress().toString().substring(1) + "异常");
    }
}

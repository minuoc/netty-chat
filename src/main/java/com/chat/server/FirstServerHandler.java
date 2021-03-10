package com.chat.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author chenlufeng
 * @date 2021/3/4
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg){
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(new Date() + ":服务器读到数据 ->" + byteBuf.toString(StandardCharsets.UTF_8));
    }

}

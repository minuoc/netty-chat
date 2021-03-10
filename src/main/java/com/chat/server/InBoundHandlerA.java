package com.chat.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author chenlufeng
 * @date 2021/3/9
 */
public class InBoundHandlerA extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception {
        System.out.println("inboundA:" + msg);
        super.channelRead(ctx, msg);
    }
}

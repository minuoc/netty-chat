package com.chat.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.StandardCharsets;

/**
 * @author chenlufeng
 * @date 2021/3/4
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx){

        //获取数据
        ByteBuf buffer = getByteBuff(ctx);
        //写数据
        ctx.channel().writeAndFlush(buffer);
    }

    private ByteBuf getByteBuff(ChannelHandlerContext ctx) {
        //1. 获取二进制抽象 ByteBuf
        ByteBuf buffer = ctx.alloc().buffer();

        //2. 写数据
        byte[] bytes = "你好,hello".getBytes(StandardCharsets.UTF_8);

        //3. 填充数据到ByteBuf
        buffer.writeBytes(bytes);


        return buffer;

    }


}

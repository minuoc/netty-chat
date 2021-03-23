package com.chat.client.handler;

import com.chat.protocol.response.GroupMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author chenlufeng
 * @date 2021/3/23
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            System.out.println("收到群[" + responsePacket.getFromGroupId() + "] 的消息：" + responsePacket.getMessage());
        }
    }
}

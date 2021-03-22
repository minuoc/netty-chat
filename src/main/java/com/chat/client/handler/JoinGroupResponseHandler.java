package com.chat.client.handler;

import com.chat.protocol.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author chenlufeng
 * @date 2021/3/22
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()){
            System.out.println("加入群聊[" + responsePacket.getGroupId() + "] 成功!");
        }else {
            System.out.println("加入群聊[" + responsePacket.getGroupId() + "] 失败!");
        }


    }
}

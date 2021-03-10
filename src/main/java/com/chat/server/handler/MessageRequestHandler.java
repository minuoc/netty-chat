package com.chat.server.handler;

import com.chat.protocol.PacketCodeC;
import com.chat.protocol.request.MessageRequestPacket;
import com.chat.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author chenlufeng
 * @date 2021/3/10
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket packet) throws Exception {

        System.out.println(new Date() + ": 收到客户端消息：" + packet.getMessage());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage(new Date() + ": 服务端回复[" + packet.getMessage() + "]");

        ctx.channel().writeAndFlush(packet);

    }
}

package com.chat.server.handler;

import com.chat.protocol.request.MessageRequestPacket;
import com.chat.protocol.response.MessageResponsePacket;
import com.chat.session.Session;
import com.chat.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author chenlufeng
 * @date 2021/3/10
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket packet) throws Exception {
        //1 得到session
        Session session = SessionUtil.getSession(ctx.channel());

        //构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(packet.getMessage());

        //获取消息接收方的 channel
        Channel toUserChannel = SessionUtil.getChannel(packet.getToUserId());

        //将消息发送给接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)){
            toUserChannel.writeAndFlush(messageResponsePacket);
        }else {
            System.err.println("[" + packet.getToUserId() + "] 不在线，发送消息失败！");
        }

    }
}

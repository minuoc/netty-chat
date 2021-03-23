package com.chat.server.handler;

import com.chat.protocol.request.GroupMessageRequestPacket;
import com.chat.protocol.response.GroupMessageResponsePacket;
import com.chat.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author chenlufeng
 * @date 2021/3/23
 */
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) throws Exception {
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(requestPacket.getToGroupId());

        GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();

        groupMessageResponsePacket.setFromGroupId(requestPacket.getToGroupId());
        groupMessageResponsePacket.setMessage(requestPacket.getMessage());
        groupMessageResponsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));

        groupMessageResponsePacket.setSuccess(true);
        channelGroup.writeAndFlush(groupMessageResponsePacket);
    }
}

package com.chat.server.handler;

import com.chat.protocol.request.ListGroupMembersRequestPacket;
import com.chat.protocol.response.ListGroupMembersResponsePacket;
import com.chat.session.Session;
import com.chat.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenlufeng
 * @date 2021/3/23
 */
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket requestPacket) throws Exception {
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        List<Session> sessionList = new ArrayList<>();
        for (Channel channel : channelGroup) {
            Session session = SessionUtil.getSession(channel);
            sessionList.add(session);
        }

        ListGroupMembersResponsePacket listGroupResponsePacket = new ListGroupMembersResponsePacket();
        listGroupResponsePacket.setGroupId(groupId);
        listGroupResponsePacket.setMembers(sessionList);
        listGroupResponsePacket.setSuccess(true);


        ctx.channel().writeAndFlush(listGroupResponsePacket);


    }
}

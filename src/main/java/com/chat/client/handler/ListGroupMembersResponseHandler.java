package com.chat.client.handler;

import com.chat.protocol.response.ListGroupMembersResponsePacket;
import com.chat.session.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenlufeng
 * @date 2021/3/23
 */
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()){
            List<Session> list = responsePacket.getMembers();
            List<String> userNameList = list.stream().map(Session::getUserName).collect(Collectors.toList());
            System.out.print("群里面有[");
            for (String userName : userNameList) {
                System.out.print(userName + ",") ;
            }
            System.out.print("]");
        }

    }
}

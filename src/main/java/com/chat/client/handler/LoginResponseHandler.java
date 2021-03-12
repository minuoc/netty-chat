package com.chat.client.handler;

import com.chat.protocol.PacketCodeC;
import com.chat.protocol.request.LoginRequestPacket;
import com.chat.protocol.response.LoginResponsePacket;
import com.chat.session.Session;
import com.chat.util.LoginUtil;
import com.chat.util.SessionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @author chenlufeng
 * @date 2021/3/10
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket packet) throws Exception {
        String userId = packet.getUserId();
        String userName = packet.getUserName();

        if (packet.isSuccess()){
            System.out.println("[" + userName + "]登录成功，userId 为: " + packet.getUserId());
            SessionUtil.bindSession(new Session(userId,userName),ctx.channel());
        } else {
            System.out.println("[" + userName + "] 登录失败 " + packet.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接被关闭!");
    }
}

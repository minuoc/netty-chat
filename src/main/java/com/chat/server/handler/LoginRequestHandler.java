package com.chat.server.handler;

import com.chat.protocol.Packet;
import com.chat.protocol.PacketCodeC;
import com.chat.protocol.request.LoginRequestPacket;
import com.chat.protocol.response.LoginResponsePacket;
import com.chat.session.Session;
import com.chat.util.LoginUtil;
import com.chat.util.SessionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @author chenlufeng
 * @date 2021/3/10
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket packet) throws Exception {

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(packet.getVersion());
        loginResponsePacket.setUserName(packet.getUsername());

        //登录校验
        if (valid(packet)) {
            //登录校验
            loginResponsePacket.setSuccess(true);
            String userId  = randomUserId();
            loginResponsePacket.setUserId(userId);
            System.out.println("[" + packet.getUsername() + "]登录成功");
            SessionUtil.bindSession(new Session(userId,packet.getUsername()), ctx.channel());
        } else {
            //校验失败
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason(packet.getUsername() + ": 登录失败");
        }
        //登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);


    }

    private String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }

    private boolean valid(LoginRequestPacket packet) {
        return true;
    }


}

package com.chat.server.handler;

import com.chat.protocol.Packet;
import com.chat.protocol.PacketCodeC;
import com.chat.protocol.request.LoginRequestPacket;
import com.chat.protocol.response.LoginResponsePacket;
import com.chat.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author chenlufeng
 * @date 2021/3/10
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket packet) throws Exception {
        //登录逻辑
        System.out.println(new Date() + ":收到客户端登录请求");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginResponsePacket.getVersion());

        //登录校验
        if (valid(packet)) {
            //登录校验
            loginResponsePacket.setSuccess(true);
            System.out.println(new Date() + ": 登录成功!");
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            //校验失败
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码校验失败");
        }

        //登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);


    }

    private boolean valid(LoginRequestPacket packet) {
        return true;
    }


}

package com.chat.client.handler;

import com.chat.protocol.PacketCodeC;
import com.chat.protocol.request.LoginRequestPacket;
import com.chat.protocol.response.LoginResponsePacket;
import com.chat.util.LoginUtil;
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
    public void channelActive(ChannelHandlerContext ctx) {
        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("flash");
        loginRequestPacket.setPassword("pwd");


        // 写数据
        ctx.channel().writeAndFlush(loginRequestPacket);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket packet) throws Exception {
        if (packet.isSuccess()){
            System.out.println(new Date() + "客户端登录成功");
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            System.out.println(new Date() + "客户端登录失败，原因：" + packet.getReason());
        }
    }
}

package com.chat.server;

import com.chat.protocol.Packet;
import com.chat.protocol.PacketCodeC;
import com.chat.protocol.request.LoginRequestPacket;
import com.chat.protocol.request.MessageRequestPacket;
import com.chat.protocol.response.LoginResponsePacket;
import com.chat.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @author chenlufeng
 * @date 2021/3/8
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg){
        System.out.println(new Date() + ":客户端开始登录");
        ByteBuf requestByteBuf = (ByteBuf)msg;

        //解码
        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);


        if (packet instanceof LoginRequestPacket){

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());

            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            //登录校验
            if (valid(loginRequestPacket)){
                //登录校验
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + ": 登录成功!");
            } else {
                //校验失败
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("账号密码校验失败");
            }

            //编码
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(),loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);

        } else if (packet instanceof MessageRequestPacket) {
            //处理消息
            MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);
            System.out.println(new Date() + ": 收到客户端消息：" + messageRequestPacket.getMessage());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage(new Date() + ": 服务端回复[" + messageRequestPacket.getMessage() + "]");

            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(),messageResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);


        }






    }

    private boolean valid(LoginRequestPacket loginRequestPacket){
        return true;
    }
}

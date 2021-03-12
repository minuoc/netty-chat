package com.chat.client;

import com.chat.client.handler.FirstClientHandler;
import com.chat.client.handler.LoginResponseHandler;
import com.chat.client.handler.MessageResponseHandler;
import com.chat.codec.PacketDecoder;
import com.chat.codec.PacketEncoder;
import com.chat.codec.Spliter;
import com.chat.protocol.PacketCodeC;
import com.chat.protocol.request.LoginRequestPacket;
import com.chat.protocol.request.MessageRequestPacket;
import com.chat.util.LoginUtil;
import com.chat.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;


import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author chenlufeng
 * @date 2021/3/4
 */
public class NettyClient {
    private static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());

                    }
                });
        connect(bootstrap, "127.0.0.1", 8081, 5);

    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功！");
                Channel channel = ((ChannelFuture) future).channel();
                //连接成功之后，启动控制台线程
                startConsoleThread(channel);

            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接");
            } else {
                //第几次重连
                int order = (MAX_RETRY - retry) + 1;
                //本次重连的间隔
                int delay = 1 << order;

                System.err.println(new Date() + ": 连接失败" +
                        "");

                bootstrap.config().group().schedule(
                        () -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);

            }
        });


    }

    private static void startConsoleThread(Channel channel) {

        Scanner sc = new Scanner(System.in);
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        new Thread(() -> {
            while (!Thread.interrupted()) {

                if (!SessionUtil.hasLogin(channel)) {
                    System.out.println("输入用户名登录：");
                    String username = sc.nextLine();
                    loginRequestPacket.setUsername(username);
                    //密码使用默认的

                    loginRequestPacket.setPassword("pwd");

                    //发送登录数据包
                   channel.writeAndFlush(loginRequestPacket);
                   waitForLoginResponse();
                } else {
                    String toUserId = sc.next();
                    String message = sc.next();
                    channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
                }


            }
        }).start();


    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){

        }
    }


}



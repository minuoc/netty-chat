package com.chat.client.command;

import com.chat.protocol.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author chenlufeng
 * @date 2021/3/20
 */
public class SendToUserConsoleCommand implements ConsoleCommand{


    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("发送消息给某个某个用户");

        String toUserId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(toUserId,message));

    }
}

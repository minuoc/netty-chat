package com.chat.client.command;

import com.chat.protocol.request.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author chenlufeng
 * @date 2021/3/23
 */
public class SendToGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        GroupMessageRequestPacket requestPacket = new GroupMessageRequestPacket();

        System.out.println("发送消息给群组:");

        String groupId = scanner.next();
        String message = scanner.next();
        requestPacket.setToGroupId(groupId);
        requestPacket.setMessage(message);
        channel.writeAndFlush(requestPacket);

    }
}

package com.chat.client.command;

import com.chat.protocol.request.ListGroupMembersRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author chenlufeng
 * @date 2021/3/22
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand{

    @Override
    public void exec(Scanner scanner, Channel channel) {
        ListGroupMembersRequestPacket listGroupRequestPacket = new ListGroupMembersRequestPacket();
        System.out.println("输入groupId,查询群成员：");

        String groupId = scanner.next();
        listGroupRequestPacket.setGroupId(groupId);

        channel.writeAndFlush(listGroupRequestPacket);
    }
}

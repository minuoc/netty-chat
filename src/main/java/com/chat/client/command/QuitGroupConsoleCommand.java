package com.chat.client.command;

import com.chat.protocol.request.QuitGroupRequestPacket;
import com.chat.protocol.response.QuitGroupResponsePacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author chenlufeng
 * @date 2021/3/22
 */
public class QuitGroupConsoleCommand implements ConsoleCommand{

    @Override
    public void exec(Scanner scanner, Channel channel) {
        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();

        System.out.print("输入 groupId,退出群聊:");
        String groupId = scanner.next();

        quitGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(quitGroupRequestPacket);


    }
}

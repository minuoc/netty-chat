package com.chat.client.command;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author chenlufeng
 * @date 2021/3/12
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}

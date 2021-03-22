package com.chat.client.command;

import com.chat.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author chenlufeng
 * @date 2021/3/20
 */
public class LoginConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        System.out.print("输入用户名登录：");
        loginRequestPacket.setUsername(scanner.next());
        loginRequestPacket.setPassword("pwd");

        //发送登录数据包
        channel.writeAndFlush(loginRequestPacket);
        waitForLoginResponse();
    }

    private void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
    }
}

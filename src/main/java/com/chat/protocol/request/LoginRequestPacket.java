package com.chat.protocol.request;

import com.chat.protocol.Packet;
import lombok.Data;

import static com.chat.protocol.command.Command.LOGIN_REQUEST;

/**
 * @author chenlufeng
 * @date 2021/3/8
 */
@Data
public class LoginRequestPacket extends Packet {

    private String userId;

    private String username;

    private String password;


    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}

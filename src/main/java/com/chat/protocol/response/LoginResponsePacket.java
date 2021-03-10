package com.chat.protocol.response;

import com.chat.protocol.Packet;
import lombok.Data;

import static com.chat.protocol.command.Command.LOGIN_RESPONSE;

/**
 * @author chenlufeng
 * @date 2021/3/8
 */
@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}

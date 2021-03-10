package com.chat.protocol.response;

import com.chat.protocol.Packet;
import lombok.Data;

import static com.chat.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @author chenlufeng
 * @date 2021/3/9
 */
@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}

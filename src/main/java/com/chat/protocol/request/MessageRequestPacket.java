package com.chat.protocol.request;

import com.chat.protocol.Packet;
import lombok.Data;

import static com.chat.protocol.command.Command.MESSAGE_REQUEST;
import static com.chat.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @author chenlufeng
 * @date 2021/3/9
 */
@Data
public class MessageRequestPacket extends Packet {

    private String message;


    @Override
    public Byte getCommand(){
        return MESSAGE_REQUEST;
    }

}

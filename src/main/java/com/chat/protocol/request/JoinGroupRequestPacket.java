package com.chat.protocol.request;

import com.chat.protocol.Packet;
import com.chat.protocol.command.Command;
import lombok.Data;

/**
 * @author chenlufeng
 * @date 2021/3/22
 */
@Data
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }
}

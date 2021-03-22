package com.chat.protocol.request;

import com.chat.protocol.Packet;
import com.chat.protocol.command.Command;
import lombok.Data;

/**
 * @author chenlufeng
 * @date 2021/3/22
 */
@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_REQUEST;
    }
}

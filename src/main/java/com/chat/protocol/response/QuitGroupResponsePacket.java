package com.chat.protocol.response;

import com.chat.protocol.Packet;
import com.chat.protocol.command.Command;
import lombok.Data;

/**
 * @author chenlufeng
 * @date 2021/3/22
 */
@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_RESPONSE;
    }
}

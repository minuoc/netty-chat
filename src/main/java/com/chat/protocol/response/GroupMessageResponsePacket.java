package com.chat.protocol.response;

import com.chat.protocol.Packet;
import com.chat.protocol.command.Command;
import com.chat.session.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenlufeng
 * @date 2021/3/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupMessageResponsePacket extends Packet {
    private String fromGroupId;

    private Session fromUser;

    private String message;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.SEND_TO_GROUP_RESPONSE;
    }
}

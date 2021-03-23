package com.chat.protocol.request;

import com.chat.protocol.Packet;
import com.chat.protocol.command.Command;
import lombok.Data;

/**
 * @author chenlufeng
 * @date 2021/3/23
 */
@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }
}

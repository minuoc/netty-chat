package com.chat.protocol.response;

import com.chat.protocol.Packet;
import com.chat.protocol.command.Command;
import com.chat.session.Session;
import lombok.Data;

import java.util.List;

/**
 * @author chenlufeng
 * @date 2021/3/23
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {
    private String groupId;

    private List<Session> members;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}

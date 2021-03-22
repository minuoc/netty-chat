package com.chat.protocol.response;

import com.chat.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.chat.protocol.command.Command.CREATE_GROUP_RESPONSE;

/**
 * @author chenlufeng
 * @date 2021/3/12
 */
@Data
public class CreateGroupResponsePacket extends Packet {

    private boolean success;

    private String groupId;

    private List<String> userNameList;


    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}

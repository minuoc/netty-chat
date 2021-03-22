package com.chat.protocol.request;

import com.chat.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.chat.protocol.command.Command.CREATE_GROUP_REQUEST;

/**
 * @author chenlufeng
 * @date 2021/3/12
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}

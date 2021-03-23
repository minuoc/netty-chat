package com.chat.protocol.request;

import com.chat.protocol.Packet;
import com.chat.protocol.command.Command;
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
public class GroupMessageRequestPacket extends Packet {
    private String toGroupId;

    private String message;


    @Override
    public Byte getCommand() {
        return Command.SEND_TO_GROUP_REQUEST;
    }
}

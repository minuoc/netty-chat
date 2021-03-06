package com.chat.protocol;

import com.chat.protocol.request.*;
import com.chat.protocol.response.*;
import com.chat.serialize.Serializer;
import com.chat.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static com.chat.protocol.command.Command.*;

/**
 * @author chenlufeng
 * @date 2021/3/8
 */
public class PacketCodeC {
    public static final int MAGIC_NUMBER = 0x12345678;

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private final Map<Byte,Class<? extends Packet>> packetTypeMap;

    private final Map<Byte,Serializer> serializerMap;

    public PacketCodeC() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);
        packetTypeMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMap.put(CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        packetTypeMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        packetTypeMap.put(JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        packetTypeMap.put(QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        packetTypeMap.put(QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        packetTypeMap.put(LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestPacket.class);
        packetTypeMap.put(LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponsePacket.class);
        packetTypeMap.put(SEND_TO_GROUP_REQUEST,GroupMessageRequestPacket.class);
        packetTypeMap.put(SEND_TO_GROUP_RESPONSE,GroupMessageResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(),serializer);
    }

    public ByteBuf encode(ByteBufAllocator byteBufAllocator,Packet packet) {
        //1. ??????ByteBuf ??????
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        //2. ????????? Java ??????
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        //3. ??????????????????
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;

    }

    public ByteBuf encode(ByteBuf byteBuf,Packet packet) {

        // ????????? Java ??????
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        //3. ??????????????????
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;

    }

    public Packet decode(ByteBuf byteBuf) {
        //?????? magic number;
        byteBuf.skipBytes(4);
        //???????????????
        byteBuf.skipBytes(1);
        //?????????????????????
        byte serializeAlgorithm = byteBuf.readByte();
        //??????
        byte command = byteBuf.readByte();

        //????????? ??????
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);



        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);
        if(requestType != null && serializer != null){
            return serializer.deserialize(requestType,bytes);
        }


        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }
}

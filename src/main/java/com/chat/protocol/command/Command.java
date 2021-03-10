package com.chat.protocol.command;

/**
 * @author chenlufeng
 * @date 2021/3/8
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;


}

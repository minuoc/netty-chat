package com.chat.protocol;

import lombok.Data;

/**
 * @author chenlufeng
 * @date 2021/3/8
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    private Byte version = 1;


    /**
     * 指令
     */
    public abstract Byte getCommand();
}

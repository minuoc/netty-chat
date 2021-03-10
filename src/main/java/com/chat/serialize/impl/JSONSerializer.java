package com.chat.serialize.impl;


import com.alibaba.fastjson.JSON;
import com.chat.serialize.Serializer;
import com.chat.serialize.SerializerAlgorithm;

/**
 * @author chenlufeng
 * @date 2021/3/8
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}

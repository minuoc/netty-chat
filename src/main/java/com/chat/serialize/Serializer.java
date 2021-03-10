package com.chat.serialize;

import com.chat.serialize.impl.JSONSerializer;

/**
 * @author chenlufeng
 * @date 2021/3/8
 */
public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();
    /**
     * 序列化算法
     */

    byte getSerializerAlgorithm();


    /**
     * java 对象转换为二进制
     */

    byte[] serialize(Object object);

    /**
     * 二进制转换成java 对象
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}

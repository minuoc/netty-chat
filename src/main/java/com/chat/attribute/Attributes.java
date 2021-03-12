package com.chat.attribute;

import com.chat.session.Session;
import io.netty.util.AttributeKey;

/**
 * @author chenlufeng
 * @date 2021/3/9
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}

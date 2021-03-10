package com.chat.util;

import com.chat.attribute.Attributes;

import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author chenlufeng
 * @date 2021/3/9
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}

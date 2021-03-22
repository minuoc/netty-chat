package com.chat.util;

import java.util.UUID;

/**
 * @author chenlufeng
 * @date 2021/3/20
 */
public class IDUtil {

    public static String randomId(){
        return UUID.randomUUID().toString().split("-")[0];
    }
}

package com.chat.util;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author chenlufeng
 * @date 2021/3/9
 */
public class ChannelInceptor implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, objects);
        after();
        return result;
    }

    public void before(){
        System.out.println("before -----" + new Date());
    }

    public void after(){
        System.out.println("after -----" + new Date());
    }
}

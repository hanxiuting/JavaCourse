package com.springdemo.springstart.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理实现
 *
 * @Author: hxt
 * @Date: 2021-06-04 16:30
 * @Description:
 */
public class ProxyHandler implements InvocationHandler {
    private Object obj;

    public ProxyHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("ding")) {
            System.out.println("proxy invoke ding begin ==>" + method.getName());
            method.invoke(obj, args);
            System.out.println("proxy invoke ding end ==>" + method.getName());
        } else {
            method.invoke(obj, args);
        }
        return null;
    }
}

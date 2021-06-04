package com.springdemo.springstart.proxyaop;

import com.springdemo.springstart.iservice.ISchool;
import com.springdemo.springstart.proxy.ProxyHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 作业第1题： 使用动态代理实现AOP
 *
 * @Author: hxt
 * @Date: 2021-06-04 17:08
 * @Description:
 */
public class ProxyRun {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ISchool school = context.getBean(ISchool.class);
        InvocationHandler handler = new ProxyHandler(school);
        ISchool proxy = (ISchool) Proxy.newProxyInstance(school.getClass().getClassLoader(), school.getClass().getInterfaces(), handler);
        proxy.ding();
    }
}

package com.rz.frame.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynaminProxy implements InvocationHandler {
    private Object subject;

    public DynaminProxy(Object subject)
    {
        this.subject = subject;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object obj = method.invoke(subject, args);
        System.out.println("end");
        return obj;
    }
}

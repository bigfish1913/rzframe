package com.rz.frame.dynamicproxy;

import com.rz.frame.dynamicproxy.IWoker;

import java.lang.reflect.Method;

public class $Proxy implements IWoker {
    private DynaminProxy target;

    public $Proxy(DynaminProxy target) {
        this.target = target;
    }

    public String sayHello(String var0, String var1) {
        try {
            Method m = IWoker.class.getDeclaredMethod("sayHello", var0.getClass(), var1.getClass());
            Object invoke = this.target.invoke(this, m, new Object[]{var0, var1});
            return (String) invoke;
        } catch (Throwable ex) {
            return null;
        }
    }
}

package com.rz.frame.dynamicproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.function.BiFunction;

public class AppTest {

    public static void main(String[] args) {
        try {
            Worker worker = new Worker();
            //
            //            IWoker proxy = (IWoker) Proxy.newProxyInstance(IWoker.class.getClassLoader(), new Class[]{IWoker.class}, new DynaminProxy(worker));
            //            proxy.sayHello("ok");
            //
//            IWoker $proxy = (IWoker) RxProxy.newProxyInstance(new DynaminProxy(worker));
//
//            $proxy.sayHello("Rz", "d");
//
//            IWoker proxy1 = (IWoker) new RzProxy().getProxy(worker.getClass().getClassLoader(), new Class[]{IWoker.class}, new DynaminProxy(worker));
//            System.out.println(proxy1.sayHello("Rz", "Rx"));

            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Worker.class);
            MethodInterceptor func = new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    System.out.println("before method run...");
                    Object result = proxy.invokeSuper(obj, args);
                    System.out.println("after method run...");
                    return result;

                }
            };

             enhancer.setCallback(func);
            IWoker  sample = (IWoker) enhancer.create();
            sample.sayHello("12312", "qweqw");


        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


    }
}

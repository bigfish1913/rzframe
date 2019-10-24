package com.rz.frame.dynamicproxy;

public class Worker  implements IWoker{
    @Override
    public String sayHello(String name,String code) {
        System.out.println("hello!"+name);
        return "return value";
    }

}

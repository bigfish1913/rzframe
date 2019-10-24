package com.rz.frame.dynamicproxy;

public class WorkerProxy {
    public void aopWorker(IWoker doWoker){
        System.out.println("before");
        doWoker.sayHello("Rz","");
        System.out.println("end");
    }
}

package com.rz.frame.Singleton;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

public class LazySingleton {
    private LazySingleton(){
        if(LazySingletonInnerClass.INSTANCE!=null){
            throw new RuntimeException("adfasd");
        }

    }

    public LazySingleton getInstance(){
        return LazySingletonInnerClass.INSTANCE;
    }

    public static class  LazySingletonInnerClass{
        public static LazySingleton INSTANCE=new LazySingleton();
    }
}

package com.rz.frame.Unsafe;

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

package com.rz.frame.dynamicproxy;

import java.lang.reflect.Proxy;

public class RxProxy {
     public static  Object newProxyInstance(DynaminProxy dynaminProxy){
         return new $Proxy( dynaminProxy);
     }
}

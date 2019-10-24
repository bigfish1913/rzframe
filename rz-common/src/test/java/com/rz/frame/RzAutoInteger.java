package com.rz.frame;

import sun.misc.Unsafe;
import sun.misc.VM;
import sun.reflect.Reflection;

import java.lang.reflect.Field;

public class RzAutoInteger {
    private volatile long counter;
    Unsafe unsafe;
    private long offset;

    public RzAutoInteger(int value) {
        this.counter = 0;
        try {
            Field  f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            offset = unsafe.objectFieldOffset(RzAutoInteger.class.getDeclaredField("counter"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void increase() {
        long var6 = unsafe.getLongVolatile(this, offset);
        while (!unsafe.compareAndSwapLong(this, offset, var6, var6 + 1))
        {
            var6 = unsafe.getLongVolatile(this, offset);
            Thread.yield();
        }
//        unsafe.getAndAddLong(this, offset, var6);
    }

    public long getCounter() {

        return counter;

    }

}

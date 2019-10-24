package com.rz.frame.cas;

import com.rz.frame.RzAutoInteger;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class RzCounter1 implements ICounter {
    private volatile long counter = 0;
    private Unsafe unsafe;
    private long offset;

    public RzCounter1() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            offset = unsafe.objectFieldOffset(RzAutoInteger.class.getDeclaredField("counter"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void increase() {
        long before = unsafe.getLongVolatile(this, offset);
        while (!unsafe.compareAndSwapLong(this, offset, before, before + 1))
        {
            before = unsafe.getLongVolatile(this, offset);
            Thread.yield();
        }
    }

    public long getCounter() {
        return counter;
    }
}

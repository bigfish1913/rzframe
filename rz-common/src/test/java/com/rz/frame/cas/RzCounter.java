package com.rz.frame.cas;

import com.rz.frame.RzAutoInteger;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class RzCounter implements ICounter {
    private volatile long counter = 0;
    private Unsafe unsafe;
    private long offset;

    public RzCounter() {
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
        unsafe.getAndAddLong(this, offset, 1);
    }

    public long getCounter() {
        return counter;
    }
}

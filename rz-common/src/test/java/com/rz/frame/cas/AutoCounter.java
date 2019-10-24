package com.rz.frame.cas;

import java.util.concurrent.atomic.AtomicLong;

public class AutoCounter implements ICounter {
    private AtomicLong count=new AtomicLong(0);
    @Override
    public    void increase() {
        count.incrementAndGet();
    }

    @Override
    public long getCounter() {
        return count.get();
    }
}

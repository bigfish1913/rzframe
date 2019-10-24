package com.rz.frame.cas;

import javax.swing.*;

public class SyncCounter implements ICounter {
    private long count=0;
    @Override
    public  synchronized void increase() {
        count++;
    }

    @Override
    public long getCounter() {
        return count;
    }
}

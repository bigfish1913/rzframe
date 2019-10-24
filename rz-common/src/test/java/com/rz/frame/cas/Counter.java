package com.rz.frame.cas;

import javax.swing.*;

public class Counter implements ICounter {
    private long count=0;
    @Override
    public void increase() {
        count++;
    }

    @Override
    public long getCounter() {
        return count;
    }
}

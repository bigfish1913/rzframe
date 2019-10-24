package com.rz.frame.cas;

class CounterClient implements Runnable {
    private ICounter c;
    private int num;

    public CounterClient(ICounter c, int num) {
        this.c = c;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            c.increase();
        }
    }
}
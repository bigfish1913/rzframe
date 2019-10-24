package com.rz.frame.Unsafe;

public class A {
    private long a; // not initialized value

     private  A() {
        this.a = 1; // initialization
    }

    public long a() { return this.a; }
}



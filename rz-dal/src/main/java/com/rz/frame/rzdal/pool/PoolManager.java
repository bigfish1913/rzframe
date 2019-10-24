package com.rz.frame.rzdal.pool;


public class PoolManager {

    private final static PoolImpl poolImp = new PoolImpl();

    public static PoolImpl getInstance() {
        return poolImp;
    }
}

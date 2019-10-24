package com.rz.frame.rzdal.pool;


public class PoolManager {

    private static class CreatePool {
        private static  PoolImpl poolImp;

        static {

            poolImp = new  PoolImpl();

        }
    }

    public static  PoolImpl getInstance() {
        return CreatePool.poolImp;
    }
}

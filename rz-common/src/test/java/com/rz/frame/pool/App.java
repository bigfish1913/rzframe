package com.rz.frame.pool;

import java.sql.ResultSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        int N=1000;
        AtomicInteger integer=new AtomicInteger();
        CountDownLatch countDownLatch = new CountDownLatch(N);
        for (int i = 0; i < N; i++) {
            Thread thread = new Thread(() -> {
                try {
                   int res= doSomeThing();
                    integer.addAndGet(res) ;
//                    System.out.println(res);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });

            thread.start();

        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
        System.out.println(integer.longValue());
        PoolManager.getInstance().shutDown();

    }

    private static Integer doSomeThing() throws Exception {
        ResultSet resultSet = PoolManager.getInstance().querySql("select * from rz_user");
//        Thread.sleep(1000);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("id"));
        }
//        System.out.println("runing");

        return 2;


    }


}

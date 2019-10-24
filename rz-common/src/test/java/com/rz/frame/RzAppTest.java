package com.rz.frame;

import java.util.ArrayList;
import java.util.List;

public class RzAppTest {

    private int count = 0;

    public static void main(String[] args) {
        long ts = System.currentTimeMillis();
        RzAutoInteger app = new RzAutoInteger(0);
        List<Thread> tList = new ArrayList<>(100);

        for (int i = 0; i < 100; i++) {
            app.increase();
            System.out.println(app.getCounter());
            //                Thread thread = new Thread(() -> {
            //                    for (int m = 0; m < 1000000; m++) {
            //                        app.increase();
            //                    }
            //                });
            //                tList.add(thread);
        }
//        //启动线程
//        for (Thread t : tList) {
//            t.start();
//        }
//        //等待所有线程执行完
//        for (Thread t : tList) {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        System.out.println(app.getCounter());
        long tend = System.currentTimeMillis();
        System.out.println(tend - ts);
    }


}

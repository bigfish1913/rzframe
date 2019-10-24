package com.rz.frame.cas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppTest {

    public static void main(String[] args) {
//        testCounter(new Counter(), Counter.class.getSimpleName());
//        testCounter(new SyncCounter(), SyncCounter.class.getSimpleName());
//        testCounter(new ReentrantLockCounter(), ReentrantLockCounter.class.getSimpleName());
//        testCounter(new RzCounter(), RzCounter.class.getSimpleName());
//        testCounter(new AutoCounter(), AutoCounter.class.getSimpleName());
//        testCounter(new RzCounter1(), RzCounter1.class.getSimpleName());
        System.out.println("hello");

    }

    public String test(){
        return "a";
    }

//    private static void testCounter(ICounter counter, String counterName) throws InterruptedException {
//        int nThreads = 100;
//        int maxValue = 1000000;
//        ExecutorService service = Executors.newFixedThreadPool(nThreads);
//        // creating instance of specific counter
//        long before = System.currentTimeMillis();
//        for (int i = 0; i < nThreads; i++) {
//            service.submit(() -> {
//                for (int j = 0; j < maxValue; j++) {
//                    counter.increase();
//                }
//            });
//        }
//
//        service.shutdown();
//        service.awaitTermination(1, TimeUnit.MINUTES);
//        long after = System.currentTimeMillis();
//        System.out.println(counterName + " Counter计算结果: " + counter.getCounter());
//        System.out.println(counterName + " Counter计算耗时:" + (after - before));
//        System.out.println("================================================");
//    }
}

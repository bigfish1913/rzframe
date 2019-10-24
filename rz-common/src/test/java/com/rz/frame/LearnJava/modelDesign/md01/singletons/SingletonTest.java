package com.rz.frame.LearnJava.modelDesign.md01.singletons;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SingletonTest {
	
	public static void main(String[] args) {
		int n = 100;
		CountDownLatch countDownLatch = new CountDownLatch(100);
		ExecutorService executorService = Executors.newFixedThreadPool(n);
		for (int i = 0; i < n; i++) {
			executorService.execute(() -> {
				Singleton01.getInstance();
				Singleton02.getInstance();
				Singleton03.getInstance();
				countDownLatch.countDown();
			});
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

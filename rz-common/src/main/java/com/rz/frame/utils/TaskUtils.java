package com.rz.frame.utils;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class TaskUtils {
	public static <T> void startWithMultiThread(List<T> list, int nThread, Consumer<T> func) {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		if (nThread <= 0) {
			return;
		}
		if (func == null) {
			return;
		}
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		Semaphore semaphore = new Semaphore(nThread);//定义几个许可
		ExecutorService executorService = Executors.newFixedThreadPool(nThread);//创建一个固定的线程池
		for (T obj : list) {
			try {
				semaphore.acquire();
				executorService.execute(() -> {
					try {
						func.accept(obj);
						
					} catch (Exception ex) {
					
					} finally {
						semaphore.release();
					}
				});
			} catch (InterruptedException e) {
			
			}
		}
		
		try {
			executorService.shutdown();
			executorService.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void startWithMultiThread(int nThread, Runnable func) {
		if (nThread <= 0) {
			return;
		}
		if (func == null) {
			return;
		}
		Semaphore semaphore = new Semaphore(nThread);//定义几个许可
		ExecutorService executorService = Executors.newFixedThreadPool(nThread);//创建一个固定的线程池
		for (int i = 0; i < nThread; i++) {
			try {
				semaphore.acquire();
				executorService.execute(() -> {
					try {
						func.run();
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
						semaphore.release();
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			executorService.shutdown();
			executorService.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}

package learnJava.modelDesign.md01.singletons;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonTest {
	
	public static void main(String[] args) {
		int n = 100;
		CountDownLatch countDownLatch = new CountDownLatch(100);
		ExecutorService executorService = Executors.newFixedThreadPool(n);
		for (int i = 0; i < n; i++) {
			executorService.execute(() -> {
				learnJava.modelDesign.md01.singletons.Singleton01.getInstance();
				Singleton01.getInstance();
				Singleton03.getInstance();
				Singleton04.getInstance();
				Singleton05.getInstance();
				Singleton06.getInstance();
				Singleton07.getInstance();
				Singleton08.getInstance();
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

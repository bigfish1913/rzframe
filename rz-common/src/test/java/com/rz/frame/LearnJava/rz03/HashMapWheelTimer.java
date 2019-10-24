package com.rz.frame.LearnJava.rz03;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

public class HashMapWheelTimer {
	public static void main(String[] args) {
		
		HashedWheelTimer timer = new HashedWheelTimer();
		timer.start();
		TimerTask task = new TimerTask() {
			@Override
			public void run(Timeout timeout) throws Exception {
				System.out.println("ok");
				timer.newTimeout(this, 5, TimeUnit.SECONDS);//结束时候再次注
			}
		};
		
		timer.newTimeout(task, 1, TimeUnit.SECONDS);
		
	}
}

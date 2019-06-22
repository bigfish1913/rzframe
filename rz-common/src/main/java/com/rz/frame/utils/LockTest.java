package com.rz.frame.utils;

public class LockTest {
	private static Object lock1=new Object();
	private static Object lock2=new Object();
	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				
				synchronized (lock1){
					System.out.println("线程1获得 lock1");
					SleepUtils.Sleep(1);
					synchronized (lock2){
						System.out.println("线程1获得 lock2");
					}
				}
				System.out.println("线程1结束 ");
			}
		}.start();
		
		new Thread() {
			@Override
			public void run() {
				
				synchronized (lock2){
					System.out.println("线程2获得 lock2");
					SleepUtils.Sleep(1);
					synchronized (lock1){
						System.out.println("线程2获得 lock1");
					}
				}
				System.out.println("线程2结束 ");
			}
		}.start();
	}
}

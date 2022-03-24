package com.koobyte.concurrency.c03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by sun on 2022/3/24.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class CounterSync {
	//~ Static fields/constants/initializer

	private int count;

	//~ Instance fields


	//~ Constructors


	//~ Methods

	// 写入同步
	public synchronized int inc() {
		this.count++;
		Thread.yield();
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return count;
	}

	// read未同步，会读取到失效的数据
	public int read() {
	// public synchronized int read() {
		return this.count;
	}

	/**
	 * 多个线程同时读取和写入count，读取count的线程可能读到失效的值
	 */
	public static void main(String[] args) {
		CounterSync sync = new CounterSync();
		int cnt = 2;
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.execute(() -> {
			// 每个线程执行
			for (int j = 0; j < 100; j++) {
				sync.inc();
			}
			System.out.println(sync.count);
		});
		executorService.execute(() -> {
			// 每个线程执行
			for (int j = 0; j < 10000; j++) {
				int c = sync.read();
				// 没有线程读取到100，证明变量count未同步
				System.out.println(c);
			}
		});
		executorService.shutdown();
	}
}

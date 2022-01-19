package com.koobyte.concurrency.c01;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.CountDownLatch;

/**
 * Created by sun on 2022/1/19.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
@ThreadSafe
public class SafeSequence {
	//~ Static fields/constants/initializer


	//~ Instance fields

	private int count;

	//~ Constructors


	//~ Methods

	// 多线程环境下，count++是一个符合操作，包括：读取、修改、写入三步操作，不是原子的
	public synchronized void increment() {
		count++;
	}

	public synchronized int getCount() {
		return count;
	}

	public static void main(String[] args) throws InterruptedException {
		int threadCount = 10;
		int incrementCount = 100;
		int expect = threadCount * incrementCount;
		SafeSequence unsafeSequence = new SafeSequence();
		CountDownLatch countDownLatch = new CountDownLatch(threadCount);
		for (int i = 0; i < threadCount; i++) {
			new Thread(() -> {
				for (int j = 0; j < incrementCount; j++) {
					unsafeSequence.increment();
					Thread.yield();
				}
				countDownLatch.countDown();
			}).start();
		}
		countDownLatch.await();
		int result = unsafeSequence.getCount();
		if (result != expect) {
			System.out.println(result);
		}
	}
}

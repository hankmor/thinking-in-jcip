package com.koobyte.concurrency.c02;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sun on 2022/1/24.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
@ThreadSafe
public class AtomicOperation2 {
	//~ Static fields/constants/initializer

	private final AtomicInteger count = new AtomicInteger(0);

	//~ Instance fields


	//~ Constructors


	//~ Methods

	public void increment() {
		count.getAndIncrement();
	}

	public void decrement() {
		count.getAndDecrement();
	}

	public void incAndDec() {
		increment();
		Thread.yield();
		decrement();
	}

	public static void main(String[] args) throws InterruptedException {
		AtomicOperation2 atomicOperation = new AtomicOperation2();
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			executorService.execute(atomicOperation::incAndDec);
		}
		executorService.shutdown();
		if (executorService.awaitTermination(1, TimeUnit.SECONDS)) {
			System.out.println(atomicOperation.count);
		}
	}
}

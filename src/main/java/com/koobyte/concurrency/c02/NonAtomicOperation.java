package com.koobyte.concurrency.c02;

import net.jcip.annotations.NotThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by sun on 2022/1/24.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
@NotThreadSafe
public class NonAtomicOperation {
	//~ Static fields/constants/initializer

	private int count;

	//~ Instance fields


	//~ Constructors


	//~ Methods

	public void increment() {
		count++;
	}

	public void decrement() {
		count--;
	}

	public void incAndDec() {
		increment();
		Thread.yield();
		decrement();
	}

	public static void main(String[] args) throws InterruptedException {
		NonAtomicOperation atomicOperation = new NonAtomicOperation();
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

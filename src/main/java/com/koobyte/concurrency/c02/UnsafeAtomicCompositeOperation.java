package com.koobyte.concurrency.c02;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sun on 2022/1/25.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class UnsafeAtomicCompositeOperation {
	//~ Static fields/constants/initializer

	private static final Random random = new Random(47);
	private final AtomicInteger lastId = new AtomicInteger(-1);
	private final AtomicInteger lastResult = new AtomicInteger();

	//~ Instance fields


	//~ Constructors


	//~ Methods

	/*
	 * 根据订单id生成订单编号
	 *
	 * generate方法存在竞态条件：
	 * 1、lastId 在 get 时，其他线程可能已经 set 了它的值。
	 * 2、lastResult 和 lastId 的set操作不能保证原子性
	 */

	public synchronized Integer generate(Integer orderId) {
		// 最后结果对应则直接返回
		if (orderId.equals(lastId.get())) {
			System.out.println("Existed : " + orderId);
			return lastResult.get();
		} else {
			// 生成订单编号，然后缓存并返回
			// 不能保证以下操作序列的原子性
			int result = doGenerate(orderId);
			lastResult.set(result);
			Thread.yield();
			lastId.set(orderId);
			return result;
		}
	}

	private Integer doGenerate(Integer orderId) {
		// 模拟生成
		return random.nextInt(1 << 24);
	}

	public static void main(String[] args) throws InterruptedException {
		UnsafeAtomicCompositeOperation operation = new UnsafeAtomicCompositeOperation();
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 10_000; i++) {
			int finalI = i;
			executorService.execute(() -> operation.generate(finalI));
		}
		executorService.shutdown();
		if (executorService.awaitTermination(100, TimeUnit.SECONDS)) {
			System.out.println("finished.");
		}
	}
}

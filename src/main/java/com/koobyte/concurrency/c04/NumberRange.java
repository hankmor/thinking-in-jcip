package com.koobyte.concurrency.c04;

import net.jcip.annotations.NotThreadSafe;

import java.sql.Time;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sun on 2022/1/18.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
@NotThreadSafe
public class NumberRange {
	//~ Static fields/constants/initializer

	private final AtomicInteger lower = new AtomicInteger(0);
	private final AtomicInteger upper = new AtomicInteger(0);

	//~ Instance fields


	//~ Constructors


	//~ Methods

	/*
	 * 本身是线程安全的状态变量（域），但是组合而成的类不是线程安全的。
	 *
	 * 一个线程调用 setLower(5)，另一个线程调用 setUpper(4)，可能得到 (5 ，4) 这样错误的范围（状态）。
	 */

	public void setLower(int lw) {
		// 超过上界值，抛出异常
		if (lw > upper.get()) {
			throw new IllegalArgumentException();
		}
		lower.set(lw);
	}

	public void setUpper(int up) {
		// 超过下届值，抛出异常
		if (up < lower.get()) {
			throw new IllegalArgumentException();
		}
		upper.set(up);
	}

	public int[] getRange() {
		return new int[]{lower.get(), upper.get()};
	}

	public static void main(String[] args) throws InterruptedException {
		testRange();
	}

	// 测试看看结果能否得到 [5, 4] 这样的错误范围，与调度的操作系统有关
	public static void testRange() throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		NumberRange numberRange = new NumberRange();
		// 如果首先执行setLower(5)，则抛出异常
		// 如果首先执行setUpper(4)，则upper被设置为4
		// 如果首先执行主线程的getRange()，此时lower和upper都是0
		// 所以，结果应该是 [0, 4] 或 [0, 0]
		int[] expect = new int[]{0, 4};
		for (int i = 0; i < 10_000_000; i++) {
			executorService.execute(() -> {
				try {
					numberRange.setLower(5);
				} catch (Exception e) {
				}
			});
			executorService.execute(() -> {
				try {
					numberRange.setUpper(4);
				} catch (Exception e) {
				}
			});
			int[] range = numberRange.getRange();
			if (!Arrays.equals(expect, range)) {
				// 可能输出 [0, 0]
				System.out.println(Arrays.toString(range));
			}
		}
		executorService.shutdown();
		if (executorService.awaitTermination(60, TimeUnit.SECONDS)) {
			System.out.println("执行完成");
		}
	}
}
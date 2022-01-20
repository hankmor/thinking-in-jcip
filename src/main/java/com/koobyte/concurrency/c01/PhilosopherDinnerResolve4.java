package com.koobyte.concurrency.c01;

import java.util.concurrent.locks.Lock;

/**
 * Created by sun on 2022/1/20.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class PhilosopherDinnerResolve4 {
	//~ Static fields/constants/initializer


	//~ Instance fields


	//~ Constructors


	//~ Methods

	/*
	 * 哲学家就餐问题解法1：每次都只能获取两个餐叉，不能一个一个的获取
	 */

	public static void main(String[] args) {
		int cnt = 5;
		// 5把叉子
		LockFork[] forks = new LockFork[cnt];
		for (int i = 0; i < cnt; i++) {
			forks[i] = new LockFork();
		}
		// 5个哲学家
		for (int i = 0; i < cnt; i++) {
			Philosopher philosopher = new BothForkPhilosopher(forks[i], forks[(i + 1) % cnt]);
			new Thread(philosopher).start();
		}
	}
}

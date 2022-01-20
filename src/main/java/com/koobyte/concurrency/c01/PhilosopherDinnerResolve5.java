package com.koobyte.concurrency.c01;

/**
 * Created by sun on 2022/1/20.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class PhilosopherDinnerResolve5 {
	//~ Static fields/constants/initializer


	//~ Instance fields


	//~ Constructors


	//~ Methods

	/*
	 * 哲学家就餐问题解法1：破坏竞争性条件，持有者主动放弃餐叉
	 */

	public static void main(String[] args) {
		int cnt = 5;
		// 5把叉子
		DelayLockFork[] forks = new DelayLockFork[cnt];
		for (int i = 0; i < cnt; i++) {
			forks[i] = new DelayLockFork();
		}
		// 5个哲学家
		for (int i = 0; i < cnt; i++) {
			Philosopher philosopher = new WaitingForkPhilosopher(forks[i], forks[(i + 1) % cnt]);
			new Thread(philosopher).start();
		}
	}
}

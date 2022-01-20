package com.koobyte.concurrency.c01;

/**
 * Created by sun on 2022/1/20.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class PhilosopherDinnerResolve1 {
	//~ Static fields/constants/initializer


	//~ Instance fields


	//~ Constructors


	//~ Methods

	/*
	 * 哲学家就餐问题解法1：给哲学家和餐叉编号，基数号的哲学家先拿左边的叉子，偶数的哲学家先拿右边的叉子。这样，前4个哲学家们两两竞争餐叉，
	 * 最后一名没有竞争。
	 */

	public static void main(String[] args) {
		int cnt = 5;
		// 5把叉子
		Fork[] forks = new Fork[cnt];
		for (int i = 0; i < cnt; i++) {
			forks[i] = new Fork();
		}
		// 5个哲学家
		for (int i = 0; i < cnt; i++) {
			Philosopher philosopher;
			// 基数号的哲学家先拿左边的叉子
			if (isOdd(i)) {
				philosopher = new Philosopher(forks[i], forks[(i + 1) % cnt]);
			} else {
				// 偶数号的哲学家先拿右边的叉子
				philosopher = new Philosopher(forks[(i + 1) % cnt], forks[i]);
			}
			new Thread(philosopher).start();
		}
	}

	public static boolean isOdd(int i) {
		return i % 2 == 0;
	}
}

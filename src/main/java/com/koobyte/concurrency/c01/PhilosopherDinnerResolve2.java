package com.koobyte.concurrency.c01;

/**
 * Created by sun on 2022/1/20.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class PhilosopherDinnerResolve2 {
	//~ Static fields/constants/initializer


	//~ Instance fields


	//~ Constructors


	//~ Methods

	/*
	 * 哲学家就餐问题解法1：更改最后一名哲学家获取餐叉的顺序，与其他4位相反。
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
			// 前4名先拿左再拿右
			if (i < cnt - 1) {
				philosopher = new Philosopher(forks[i], forks[(i + 1) % cnt]);
			} else {
				// 最后一名顺序相反
				philosopher = new Philosopher(forks[(i + 1) % cnt], forks[i]);
			}
			new Thread(philosopher).start();
		}
	}
}

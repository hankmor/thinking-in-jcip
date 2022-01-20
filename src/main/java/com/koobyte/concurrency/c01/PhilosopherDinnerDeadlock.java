package com.koobyte.concurrency.c01;

/**
 * Created by sun on 2022/1/20.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class PhilosopherDinnerDeadlock {
	//~ Static fields/constants/initializer


	//~ Instance fields


	//~ Constructors


	//~ Methods

	/*
	 * 哲学家就餐问题
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
			Philosopher philosopher = new Philosopher(forks[i], forks[(i + 1) % cnt]);
			new Thread(philosopher).start();
		}
	}
}

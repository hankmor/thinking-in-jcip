package com.koobyte.concurrency.c01;

/**
 * Created by sun on 2022/1/20.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class PhilosopherDinnerResolve3 {
	//~ Static fields/constants/initializer


	//~ Instance fields


	//~ Constructors


	//~ Methods

	/*
	 * 哲学家就餐问题解法1：按照全局统一的顺序获取餐叉，先获取小编号，在获取大编号
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
			Philosopher philosopher = new GlobalForkOrderPhilosopher(forks[i], forks[(i + 1) % cnt]);
			new Thread(philosopher).start();
		}
	}
}

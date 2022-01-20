package com.koobyte.concurrency.c01;

import java.util.concurrent.TimeUnit;

/**
 * Created by sun on 2022/1/20.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class WaitingForkPhilosopher extends Philosopher {
	//~ Static fields/constants/initializer


	//~ Instance fields

	private final DelayLockFork lockForkLeft;
	private final DelayLockFork lockForkRight;

	//~ Constructors

	public WaitingForkPhilosopher(DelayLockFork left, DelayLockFork right) {
		super(left, right);
		this.lockForkLeft = left;
		this.lockForkRight = right;
	}

	//~ Methods

	@Override
	public void run() {
		try {
			while (true) {
				this.thinking();
				// 一段时间内，左边的餐叉可用则拿起
				if (this.lockForkLeft.waitUsing(10 + random.nextInt(100), TimeUnit.MILLISECONDS)) {
					System.out.println(this + " got " + this.left);
					TimeUnit.MILLISECONDS.sleep(500);
					// 一段时间内，右边的餐叉可用则拿起，否则放弃左边的
					if (this.lockForkRight.waitUsing(10 + random.nextInt(100), TimeUnit.MILLISECONDS)) {
						System.out.println(this + " got " + this.right);
						this.eating();
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

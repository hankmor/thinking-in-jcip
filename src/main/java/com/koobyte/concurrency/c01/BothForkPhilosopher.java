package com.koobyte.concurrency.c01;

import java.util.concurrent.TimeUnit;

/**
 * Created by sun on 2022/1/20.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class BothForkPhilosopher extends Philosopher {
	//~ Static fields/constants/initializer


	//~ Instance fields

	private final LockFork lockForkLeft;
	private final LockFork lockForkRight;

	//~ Constructors

	public BothForkPhilosopher(LockFork left, LockFork right) {
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
				if (this.lockForkLeft.canUse() && this.lockForkRight.canUse()) {
					System.out.println(this + " got " + this.left + " and " + this.right);
					TimeUnit.MILLISECONDS.sleep(500);
					this.eating();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

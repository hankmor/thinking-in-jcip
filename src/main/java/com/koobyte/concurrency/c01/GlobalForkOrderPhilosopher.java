package com.koobyte.concurrency.c01;

import java.util.concurrent.TimeUnit;

/**
 * Created by sun on 2022/1/20.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class GlobalForkOrderPhilosopher extends Philosopher {
	//~ Static fields/constants/initializer


	//~ Instance fields


	//~ Constructors

	public GlobalForkOrderPhilosopher(Fork left, Fork right) {
		super(left, right);
	}


	//~ Methods

	@Override
	public void run() {
		try {
			while (true) {
				this.thinking();
				// 按照id从小到大获取
				if (this.left.getId() < this.right.getId()) {
					synchronized (this.left) {
						System.out.println(this + " got " + this.left);
						TimeUnit.MILLISECONDS.sleep(500);
						synchronized (this.right) {
							System.out.println(this + " got " + this.right);
							this.eating();
						}
					}
				} else if (this.left.getId() > this.right.getId()) {
					synchronized (this.right) {
						System.out.println(this + " got " + this.left);
						TimeUnit.MILLISECONDS.sleep(500);
						synchronized (this.left) {
							System.out.println(this + " got " + this.right);
							this.eating();
						}
					}
				} else {
					// id不可能相同
					throw new IllegalStateException();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

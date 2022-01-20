package com.koobyte.concurrency.c01;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by sun on 2022/1/20.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class Philosopher implements Runnable {
	//~ Static fields/constants/initializer

	protected static int number = 0;
	protected static final Random random = new Random(47);

	//~ Instance fields

	protected final int id = number++;
	protected final Fork left;
	protected final Fork right;

	//~ Constructors


	//~ Methods

	public Philosopher(Fork left, Fork right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public void run() {
		try {
			while (true) {
				this.thinking();
				// 先去获取叉子
				synchronized (this.left) {
					System.out.println(this + " got " + this.left);
					// 睡眠500ms，让死锁更快发生
					TimeUnit.MILLISECONDS.sleep(500);
					synchronized (this.right) {
						System.out.println(this + " got " + this.right);
						this.eating();
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 思考
	public void thinking() throws InterruptedException {
		System.out.println(this + " is thinking...");
		TimeUnit.MILLISECONDS.sleep(20 + random.nextInt(50));
	}

	public void eating() throws InterruptedException {
		System.out.println(this + " is eating...");
		TimeUnit.MILLISECONDS.sleep(10 + random.nextInt(50));
	}

	@Override
	public String toString() {
		return "philosopher" + id;
	}
}

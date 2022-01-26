package com.koobyte.concurrency.c03;

import net.jcip.annotations.NotThreadSafe;

/**
 * Created by sun on 2022/1/26.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
@NotThreadSafe
public class NoVisibility {
	//~ Static fields/constants/initializer


	//~ Instance fields

	private static boolean ready;
	private static int anInt;

	//~ Constructors


	//~ Methods

	/*
	 * 内存可见性问题示例：VariableReader可能看不到ready和anInt更改后的值
	 */

	private static class VariableReader implements Runnable {
		@Override
		public void run() {
			while (!ready) {
				Thread.yield();
			}
			System.out.println(anInt);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new Thread(new VariableReader(), "read").start();
		anInt = 47;
		ready = true;
	}
}

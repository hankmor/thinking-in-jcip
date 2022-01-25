package com.koobyte.concurrency.c02;

/**
 * Created by sun on 2022/1/25.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class SynchronizedDemo {
	//~ Static fields/constants/initializer


	//~ Instance fields


	//~ Constructors


	//~ Methods

	public synchronized void method1() {

	}

	public synchronized static void method2() {

	}

	public void method3() {
		synchronized (this) {

		}
	}
}

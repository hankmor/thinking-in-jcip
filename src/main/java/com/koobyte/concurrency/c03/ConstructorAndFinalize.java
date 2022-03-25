package com.koobyte.concurrency.c03;

import java.util.concurrent.TimeUnit;

/**
 * Created by sun on 2022/3/25.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class ConstructorAndFinalize {
	//~ Static fields/constants/initializer


	//~ Instance fields


	//~ Constructors

	public ConstructorAndFinalize() {
		System.out.println("Constructor...");
	}

	//~ Methods

	protected void finalize() throws Throwable {
		System.out.println("finalizing...");
	}

	public static void main(String[] args) {
		ConstructorAndFinalize constructorAndFinalize = new ConstructorAndFinalize();
		// 清除引用以便垃圾回收
		constructorAndFinalize = null;
		// 执行垃圾回收，此时会触发finalize方法
		System.gc();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		Constructor...
		finalizing...
		*/
	}
}

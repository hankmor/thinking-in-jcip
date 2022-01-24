package com.koobyte.concurrency.c02;

import net.jcip.annotations.NotThreadSafe;

/**
 * Created by sun on 2022/1/24.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
@NotThreadSafe
public class LazyInitRace {
	//~ Static fields/constants/initializer


	//~ Instance fields

	private static LazyInitRace instance;

	//~ Constructors



	//~ Methods

	/*
	 * 竞态条件示例。
	 */

	public static LazyInitRace getInstance() {
		// 这里存在竞态条件，多个线程都可能看到 instance 为null
		if (instance == null) {
			instance = new LazyInitRace();
		}
		return instance;
	}
}

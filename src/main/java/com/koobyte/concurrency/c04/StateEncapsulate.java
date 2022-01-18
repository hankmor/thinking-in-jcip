package com.koobyte.concurrency.c04;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Created by sun on 2022/1/18.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
@ThreadSafe
public class StateEncapsulate {
	//~ Static fields/constants/initializer

	// 封装决定状态的域
	@GuardedBy("this")
	private int count;

	//~ Instance fields


	//~ Constructors


	//~ Methods

	// 通过对外发布的方法来判断线程安全性
	public synchronized int getCount() {
		return count;
	}

	public synchronized void setCount(int count) {
		this.count = count;
	}
}

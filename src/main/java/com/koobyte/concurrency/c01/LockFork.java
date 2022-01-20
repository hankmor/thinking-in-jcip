package com.koobyte.concurrency.c01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by sun on 2022/1/20.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class LockFork extends Fork {
	//~ Static fields/constants/initializer

	protected static final Lock lock = new ReentrantLock();

	//~ Instance fields


	//~ Constructors


	//~ Methods

	// 当前餐叉是否可用，可用则立即获取
	public boolean canUse() {
		return lock.tryLock();
	}
}

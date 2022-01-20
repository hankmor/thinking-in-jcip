package com.koobyte.concurrency.c01;

import java.util.concurrent.TimeUnit;

/**
 * Created by sun on 2022/1/20.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class DelayLockFork extends LockFork {
	//~ Static fields/constants/initializer


	//~ Instance fields


	//~ Constructors


	//~ Methods

	// 等待一段时间，如果这段时间内餐叉可用，则使用之，否则，不用
	public boolean waitUsing(long time, TimeUnit timeUnit) throws InterruptedException {
		return lock.tryLock(time, timeUnit);
	}
}

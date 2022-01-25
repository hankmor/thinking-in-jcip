package com.koobyte.concurrency.c02;

/**
 * Created by sun on 2022/1/25.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class SyncVariable {
	//~ Static fields/constants/initializer


	//~ Instance fields

	private int var;

	//~ Constructors


	//~ Methods

	/*
	 * 同步变量，读取和修改都需要同步，以防止读取到脏数据和覆盖写入
	 */

	public synchronized int getVar() {
		return var;
	}

	public synchronized void setVar(int var) {
		this.var = var;
	}
}

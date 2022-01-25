package com.koobyte.concurrency.c02;

/**
 * Created by sun on 2022/1/25.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class SyncVariable1 {
	//~ Static fields/constants/initializer


	//~ Instance fields

	private volatile int var;

	//~ Constructors


	//~ Methods

	/*
	 * 使用 volatile 修饰变量
	 */

	public int getVar() {
		return var;
	}

	public void setVar(int var) {
		this.var = var;
	}
}

package com.koobyte.concurrency.c01;

/**
 * Created by sun on 2022/1/20.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class Fork {
	//~ Static fields/constants/initializer


	//~ Instance fields

	protected static int number = 0;
	protected final int id = number++;

	//~ Constructors


	//~ Methods

	@Override
	public String toString() {
		return "fork" + id;
	}

	public int getId() {
		return id;
	}
}

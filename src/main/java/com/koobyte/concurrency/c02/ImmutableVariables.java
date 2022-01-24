package com.koobyte.concurrency.c02;

import net.jcip.annotations.Immutable;

/**
 * Created by sun on 2022/1/24.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
@Immutable
public class ImmutableVariables {
	//~ Static fields/constants/initializer


	//~ Instance fields

	private final int x;
	private final int y;

	//~ Constructors

	public ImmutableVariables(int x, int y) {
		this.x = x;
		this.y = y;
	}

	//~ Methods

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}

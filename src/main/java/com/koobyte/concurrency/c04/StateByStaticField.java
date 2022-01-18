package com.koobyte.concurrency.c04;

import net.jcip.annotations.NotThreadSafe;

/**
 * Created by sun on 2022/1/18.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
@NotThreadSafe
public class StateByStaticField {
	//~ Static fields/constants/initializer

	// 公共静态域，无法判断其线程安全性
	public static int count;

	//~ Instance fields


	//~ Constructors


	//~ Methods
}

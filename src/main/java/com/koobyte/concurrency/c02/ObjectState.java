package com.koobyte.concurrency.c02;

/**
 * Created by sun on 2022/1/24.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class ObjectState {
	//~ Static fields/constants/initializer


	//~ Instance fields

	// 可变但是不会再更改
	private static int number = 0;
	// 不可变域
	public static final int id = number++;
	// 复杂域
	private InnerStateClass1 innerState;

	//~ Constructors


	//~ Methods

	/*
	 * 展示复杂对象的状态
	 */

	public InnerStateClass1 getInnerState() {
		return innerState;
	}

	public void setInnerState(InnerStateClass1 innerState) {
		this.innerState = innerState;
	}
}

class InnerStateClass1 {
	// 状态由域决定
	public int state;
	// 依赖对象的状态决定了类的状态
	private InnerStateClass2 innerState;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public InnerStateClass2 getInnerState() {
		return innerState;
	}

	public void setInnerState(InnerStateClass2 innerState) {
		this.innerState = innerState;
	}
}

class InnerStateClass2 {
	// 状态由静态域决定
	public static int staticField;

	public static int getStaticField() {
		return staticField;
	}

	public static void setStaticField(int staticField) {
		InnerStateClass2.staticField = staticField;
	}
}

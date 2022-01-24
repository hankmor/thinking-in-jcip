package com.koobyte.concurrency.c02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by sun on 2022/1/24.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class SynchronizedList {
	//~ Static fields/constants/initializer

	private static final Random random = new Random(47);
	private static final ExecutorService executorService = Executors.newCachedThreadPool();

	//~ Instance fields


	//~ Constructors


	//~ Methods

	public static void main(String[] args) throws InterruptedException {
		int size = 100;
		SynchronizedList main = new SynchronizedList();
		List<Object> synchronizedList = Collections.synchronizedList(main.prepare(size));
		// 一个线程遍历
		executorService.execute(() -> main.each(synchronizedList));
		// 另一个线程删除
		executorService.execute(() -> main.remove(synchronizedList));
		// 再来一个线程清空list
		executorService.execute(synchronizedList::clear);
		System.out.println(synchronizedList.size());
		executorService.shutdown();
		/*
		输出：0
		 */
	}

	public List<Object> prepare(int size) {
		ArrayList<Object> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			list.add(i);
		}
		return list;
	}

	public void remove(List<Object> list) {
		try {
			// 注意，这里必须使用同一个list对象作为锁
			synchronized (list) {
				while (!list.isEmpty()) {
					list.remove(random.nextInt(list.size()));
					TimeUnit.MILLISECONDS.sleep(10);
				}
			}
		} catch (InterruptedException ignored) {
		}
	}

	public void each(List<Object> list) {
		try {
			// 注意，这里必须使用同一个list对象作为锁
			synchronized (list) {
				for (Object o : list) {
					System.out.println(o);
					TimeUnit.MILLISECONDS.sleep(10);
				}
			}
		} catch (InterruptedException ignored) {
		}
	}
}

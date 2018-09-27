package com.senlin.jvm.concurrent;

/**
 * volatile 关键字
 * 1. volatile 只能保证变量的可见性，不能保证变量的原子性
 * 2. volatile 变量能禁止指令重排序优化
 *
 * @author gsl
 * @date 2018/8/1 22:48.
 */
public class VolatileKeyword {

    private static final int THREAD_COUNT = 10;

    private static volatile int count = 0;

    private static void increase() {
        count++;
    }

    public static void main(String[] args) {

        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        //count = 9420 ，导致这样的结果是 count++ 并非原子操作
        System.out.println(count);
    }

}

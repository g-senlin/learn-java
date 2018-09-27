package com.senlin.singleton;

/**
 * 饿汉式单例 线程安全
 * @author gsl
 * @date 2018/9/25 21:37.
 */
public class Singleton1 {

    /** 类加载时初始化 实例，线程安全*/
    private static final Singleton1 INSTANCE = new Singleton1();

    /**
     * 私有化构造
     */
    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }

}

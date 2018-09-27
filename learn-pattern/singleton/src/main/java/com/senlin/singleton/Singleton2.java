package com.senlin.singleton;

/**
 * 懒汉式单例 线程不安全
 * @author gsl
 * @date 2018/9/25 21:44.
 */
public class Singleton2 {

    private static Singleton2 instance = null;

    /**
     * 私有化构造
     */
    private Singleton2() {
    }

    /**
     * 第一次获取实例时初始化，线程不安全
     * @return
     */
    public static Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}

package com.senlin.singleton;

/**
 * 懒汉式单例 线程安全
 * @author gsl
 * @date 2018/9/25 21:51.
 */
public class Singleton3 {

    private static Singleton3 instance = null;

    /**
     * 私有化构造
     */
    private Singleton3() {
    }

    /**
     * 通过 synchronized 同步锁保证线程安全，始终只允许单线程访问，锁的粒度太大，锁的粒度应该尽可能的小
     * @return
     */
    public static synchronized Singleton3 getInstance(){
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}

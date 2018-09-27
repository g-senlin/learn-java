package com.senlin.singleton;

/**
 * 懒汉式单例 双重锁检查，线程安全
 * @author gsl
 * @date 2018/9/25 21:56.
 */
public class Singleton5 {

    /**
     * volatile 的作用：1. 保证变量的可见性 2. 通过内存屏障防止 指令重排
     */
    private static volatile Singleton5 instance = null;

    /**
     * 私有化构造
     */
    private Singleton5() {
    }

    /**
     *  双重锁检查
     *
     * @return
     */
    public static Singleton5 getInstance() {
        //第一重检查
        if (instance == null) {
            synchronized (Singleton5.class) {
                // 第二重检查
                if (instance == null) {
                    //此处可能出现指令重排
                    //1. 为 Singleton5 对象分配内存 2. 初始化 Singleton5 对象 3.把对象所在地址赋值给 instance 引用变量
                    // 此处 instance 变量被 volatile 修饰，会通过内存屏障，防止指令重排
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}

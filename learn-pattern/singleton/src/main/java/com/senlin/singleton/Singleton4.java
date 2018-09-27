package com.senlin.singleton;

/**
 * 懒汉式单例 双重锁检查，此处 instance 未用 volatile 关键字修饰，可能出现线程安全问题
 * @author gsl
 * @date 2018/9/25 21:56.
 */
public class Singleton4 {

    private static Singleton4 instance = null;

    /**
     * 私有化构造
     */
    private Singleton4() {
    }

    /**
     *  双重锁检查
     *
     * @return
     */
    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    //此处可能出现指令重排
                    //1. 为 Singleton4 对象分配内存 2. 初始化 Singleton4 对象 3.把对象所在地址赋值给 instance 引用变量
                    //如果指令重排后的顺序变为 1-> 3 -> 2 ，在高并发情况下可能获取到一个没有执行第 2 步初始化的对象
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}

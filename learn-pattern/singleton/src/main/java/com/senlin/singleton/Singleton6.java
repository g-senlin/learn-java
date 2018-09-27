package com.senlin.singleton;

/**
 * 懒汉式 静态内部类方式，无锁实现方式，既解决了安全问题，又解决了性能问题
 * @author gsl
 * @date 2018/9/26 21:34.
 */
public class Singleton6 {

    /**
     * 私有化构造
     */
    private Singleton6() {
    }

    /**
     * 通过静态内部类生成单例类实例
     */
    private static class LazyHolder {
        private static final Singleton6 INSTANCE = new Singleton6();
    }

    /**
     * 获取实例接口
     * @return
     */
    public static Singleton6 getInstance(){
        //在使用时才去初始化　LazyHolder　的　INSTANCE　属性，通过 jvm 类加载机制保证线程安全
        return LazyHolder.INSTANCE;
    }
}


package com.senlin.singleton;

/**
 * 懒汉式单例 枚举的方式 无锁实现方式，既解决了安全问题，又解决了性能问题
 * 这种方式不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象
 * @author gsl
 * @date 2018/9/26 21:58.
 */
public class Singleton7 {

    private enum LazyHolderEnum{
        INSTANCE;

        private Singleton7 instance;

        /**
         * 在构造时初始化 instance
         */
        private LazyHolderEnum(){
            this.instance= new Singleton7();
        }

        private Singleton7 getInstance() {
            return this.instance;
        }
    }

    /**
     * 私有化构造
     */
    private Singleton7() {
    }

    /**
     * 获取实例
     * @return
     */
    public static Singleton7 getInstance() {
       return LazyHolderEnum.INSTANCE.getInstance();
    }
}

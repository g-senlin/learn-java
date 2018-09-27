package com.senlin.singleton;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * 单例测试
 * @author gsl
 * @date 2018/9/26 22:12.
 */
public class SingletonTest {
    private int count = 100;
    private CountDownLatch countDownLatch;
    private Set<Object> set;

    @Before
    public void init() {
        count = 100;
        countDownLatch = new CountDownLatch(count);
        set = Collections.synchronizedSet(new HashSet<>());
    }

    @Test
    public void testSingleton1() {
        for (int i = 0; i < count; i++) {
            new Thread(() -> set.add(Singleton1.getInstance())).start();
            countDownLatch.countDown();
        }

        try {
            //等待所有线程全部完成，最终输出结果
            countDownLatch.await();
            Assert.assertTrue(set.size() == 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSingleton2() {
        for (int i = 0; i < count; i++) {
            new Thread(() -> set.add(Singleton2.getInstance())).start();
            countDownLatch.countDown();
        }

        try {
            //等待所有线程全部完成，最终输出结果
            countDownLatch.await();
            //出现线程安全问题时，set.size() > 1
//            Assert.assertTrue(set.size() == 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSingleton5() {
        for (int i = 0; i < count; i++) {
            new Thread(() -> set.add(Singleton5.getInstance())).start();
            countDownLatch.countDown();
        }

        try {
            //等待所有线程全部完成，最终输出结果
            countDownLatch.await();
            Assert.assertTrue(set.size() == 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSingleton6() {
        for (int i = 0; i < count; i++) {
            new Thread(() -> set.add(Singleton6.getInstance())).start();
            countDownLatch.countDown();
        }

        try {
            //等待所有线程全部完成，最终输出结果
            countDownLatch.await();
            Assert.assertTrue(set.size() == 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSingleton7() {
        for (int i = 0; i < count; i++) {
            new Thread(() -> set.add(Singleton7.getInstance())).start();
            countDownLatch.countDown();
        }

        try {
            //等待所有线程全部完成，最终输出结果
            countDownLatch.await();
            Assert.assertTrue(set.size() == 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
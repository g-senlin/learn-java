package com.senlin.proxy.cglib;

import com.senlin.proxy.jdk.Zhangsan;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

/**
 * @author gsl
 * @date 2018/9/27 0:33.
 */
public class CglibProxyTest {

    @Test
    public void testCglibProxy() {
        new ProxyPerson<Lisi>().getProxyInstance(Lisi.class).say();
    }

    @Test
    public void testCglibProxy2() {
        new ProxyPerson<Zhangsan>().getProxyInstance(Zhangsan.class).say();
    }
}
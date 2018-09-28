package com.senlin.proxy.cglib;

import com.google.common.io.Files;
import com.senlin.proxy.jdk.IPerson;
import com.senlin.proxy.jdk.Zhangsan;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.IOException;
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

    @Test
    public void testCglibProxyGenerateProxyClass() {
        Zhangsan proxy = new ProxyPerson<Zhangsan>().getProxyInstance(Zhangsan.class);
        proxy.say();
        byte[] proxyClass = ProxyGenerator.generateProxyClass(proxy.getClass().getSimpleName(),
                new Class[]{proxy.getClass()});
        try {
            Files.write(proxyClass,new File(proxy.getClass().getName() + ".class"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
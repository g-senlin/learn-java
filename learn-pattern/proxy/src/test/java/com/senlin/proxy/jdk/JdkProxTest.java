package com.senlin.proxy.jdk;

import com.google.common.io.Files;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.IOException;

/**
 * @author gsl
 * @date 2018/9/27 0:03.
 */
public class JdkProxTest {


    @Test
    public void testJdkProxy() {
        IPerson proxy = new ProxyPerson<IPerson>(new Zhangsan()).getProxyInstance();
        proxy.say();
    }

    @Test
    public void testJdkProxyGenerateProxyClass() {
        IPerson proxy = new ProxyPerson<IPerson>(new Zhangsan()).getProxyInstance();
        byte[] proxyClass = ProxyGenerator.generateProxyClass(proxy.getClass().getSimpleName(),
                new Class[]{proxy.getClass()});
        try {
            Files.write(proxyClass,new File(proxy.getClass().getName() + ".class"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        proxy.say();
    }
}
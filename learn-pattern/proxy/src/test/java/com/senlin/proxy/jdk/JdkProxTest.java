package com.senlin.proxy.jdk;

import com.senlin.proxy.cglib.Lisi;
import org.junit.Test;

/**
 * @author gsl
 * @date 2018/9/27 0:03.
 */
public class JdkProxTest {

    @Test
    public void testJdkProxy() {
        new ProxyPerson<IPerson>(new Zhangsan()).getProxyInstance().say();
    }
}
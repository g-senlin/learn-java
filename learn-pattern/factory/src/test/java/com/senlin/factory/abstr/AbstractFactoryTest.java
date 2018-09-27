package com.senlin.factory.abstr;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * 抽象工厂测试
 *
 * @author gsl
 * @date 2018/9/26 23:44.
 */
public class AbstractFactoryTest {

    @Test
    public void getCar() {
        DefaultFactory defaultFactory = new DefaultFactory();
        assertTrue("TOYOTA".equals(defaultFactory.getCar("TOYOTA").getName()));
        assertTrue("Benz".equals(defaultFactory.getCar("Benz").getName()));
        assertTrue("BMW".equals(defaultFactory.getCar("BMW").getName()));
    }


    @Test(expected = IllegalArgumentException.class)
    public void getCar_not_product() {
        DefaultFactory defaultFactory = new DefaultFactory();
        assertTrue("BYD".equals(defaultFactory.getCar("BYD").getName()));
    }

}
package com.senlin.factory.simple;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * 简单工厂测试
 *
 * @author gsl
 * @date 2018/9/26 23:07.
 */
public class SimpleFactoryTest {

    @Test
    public void getToyotaCar() {
        assertTrue("TOYOTA".equals(SimpleFactory.getCar("TOYOTA").getName()));
    }

    @Test
    public void getBenzCar() {
        assertTrue("Benz".equals(SimpleFactory.getCar("Benz").getName()));
    }

    @Test
    public void getBmwCar() {
        assertTrue("BMW".equals(SimpleFactory.getCar("BMW").getName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getBydCar() {
        assertTrue("BYD".equals(SimpleFactory.getCar("BYD").getName()));
    }

}
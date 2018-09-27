package com.senlin.factory.func;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * 工厂方法模式：各个产品的生产商拥有各自的工厂，用户需要关心产品的生产商
 * @author gsl
 * @date 2018/9/26 23:23.
 */
public class FactoryFunctionTest {

    @Test
    public void getToyotaCar() {
        assertTrue("TOYOTA".equals(new ToyotaFactory().getCar().getName()));
    }

    @Test
    public void getBenzCar() {
        assertTrue("Benz".equals(new BenzFactory().getCar().getName()));
    }

    @Test
    public void getBmwCar() {
        assertTrue("BMW".equals(new BmwFactory().getCar().getName()));
    }

}
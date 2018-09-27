package com.senlin.factory.simple;

import com.senlin.factory.car.Benz;
import com.senlin.factory.car.Bmw;
import com.senlin.factory.car.ICar;
import com.senlin.factory.car.Toyota;

/**
 * 简单工厂
 *
 * @author gsl
 * @date 2018/9/26 23:03.
 */
public class SimpleFactory {

    public static ICar getCar(String name) {
        if ("BMW".equalsIgnoreCase(name)) {
            return new Bmw();
        } else if ("Benz".equalsIgnoreCase(name)) {
            return new Benz();
        } else if ("TOYOTA".equalsIgnoreCase(name)) {
            return new Toyota();
        } else {
            throw new IllegalArgumentException("不生产" + name);
        }
    }
}
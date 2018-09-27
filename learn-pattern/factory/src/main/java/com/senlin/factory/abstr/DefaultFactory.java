package com.senlin.factory.abstr;

import com.senlin.factory.car.ICar;

/**
 * 默认工厂
 *
 * @author gsl
 * @date 2018/9/26 23:42.
 */
public class DefaultFactory extends AbstractFactory {

    /** 默认生产的车 */
    private BmwFactory bmwFactory = new BmwFactory();

    @Override
    protected ICar getCar() {
        return bmwFactory.getCar();
    }
}

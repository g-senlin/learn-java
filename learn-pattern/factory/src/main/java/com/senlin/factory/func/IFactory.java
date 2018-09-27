package com.senlin.factory.func;

import com.senlin.factory.car.ICar;

/**
 * 工厂接口 定义如何生产车
 * @author gsl
 * @date 2018/9/26 23:19.
 */
public interface IFactory {

    /**
     * 生产车
     * @return
     */
    ICar getCar();
}

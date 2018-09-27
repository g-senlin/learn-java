package com.senlin.factory.func;

import com.senlin.factory.car.ICar;
import com.senlin.factory.car.Toyota;

/**
 * @author gsl
 * @date 2018/9/26 23:21.
 */
public class ToyotaFactory implements IFactory {

    @Override
    public ICar getCar() {
        return new Toyota();
    }
}

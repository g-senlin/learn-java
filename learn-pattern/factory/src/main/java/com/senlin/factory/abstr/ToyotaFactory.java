package com.senlin.factory.abstr;

import com.senlin.factory.car.ICar;
import com.senlin.factory.car.Toyota;

/**
 * @author gsl
 * @date 2018/9/26 23:21.
 */
public class ToyotaFactory extends AbstractFactory {

    @Override
    public ICar getCar() {
        return new Toyota();
    }
}

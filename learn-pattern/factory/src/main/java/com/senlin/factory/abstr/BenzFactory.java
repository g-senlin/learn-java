package com.senlin.factory.abstr;

import com.senlin.factory.car.Benz;
import com.senlin.factory.car.ICar;
import com.senlin.factory.func.IFactory;

/**
 * @author gsl
 * @date 2018/9/26 23:21.
 */
public class BenzFactory extends AbstractFactory {

    @Override
    public ICar getCar() {
        return new Benz();
    }
}

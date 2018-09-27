package com.senlin.factory.func;

import com.senlin.factory.car.Benz;
import com.senlin.factory.car.Bmw;
import com.senlin.factory.car.ICar;

/**
 * @author gsl
 * @date 2018/9/26 23:21.
 */
public class BmwFactory implements IFactory {

    @Override
    public ICar getCar() {
        return new Bmw();
    }
}

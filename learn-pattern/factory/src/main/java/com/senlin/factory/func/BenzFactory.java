package com.senlin.factory.func;

import com.senlin.factory.car.Benz;
import com.senlin.factory.car.ICar;
import jdk.nashorn.internal.ir.IfNode;

/**
 * @author gsl
 * @date 2018/9/26 23:21.
 */
public class BenzFactory implements IFactory {

    @Override
    public ICar getCar() {
        return new Benz();
    }
}

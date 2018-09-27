package com.senlin.factory.abstr;

import com.senlin.factory.car.ICar;
import com.senlin.factory.func.BenzFactory;
import com.senlin.factory.func.BmwFactory;

/**
 * @author gsl
 * @date 2018/9/26 23:35.
 */
public abstract class AbstractFactory {


    protected abstract ICar getCar();

    //这段代码就是动态配置的功能
    //固定模式的委派
    public ICar getCar(String name) {
        if ("BMW".equalsIgnoreCase(name)) {
            return new BmwFactory().getCar();
        } else if ("Benz".equalsIgnoreCase(name)) {
            return new BenzFactory().getCar();
        } else if ("TOYOTA".equalsIgnoreCase(name)) {
            return new ToyotaFactory().getCar();
        } else {
            throw new IllegalArgumentException("不生产" + name);
        }
    }
}

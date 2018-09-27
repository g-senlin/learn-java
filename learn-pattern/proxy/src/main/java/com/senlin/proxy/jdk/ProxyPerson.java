package com.senlin.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk 代理
 *
 * @author gsl
 * @date 2018/9/26 23:58.
 */
public class ProxyPerson<T> implements InvocationHandler {

    private T target;

    public ProxyPerson(T target) {
        this.target = target;
    }

    public T getProxyInstance() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("befor say");
        method.invoke(target, args);
        System.out.println("after say");
        return null;
    }
}

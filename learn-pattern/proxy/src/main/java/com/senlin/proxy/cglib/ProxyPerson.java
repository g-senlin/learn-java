package com.senlin.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib 代理
 *
 * @author gsl
 * @date 2018/9/27 0:14.
 */
public class ProxyPerson<T> implements MethodInterceptor {

    public T getProxyInstance(Class clazz) {
        Enhancer enhancer = new Enhancer();
        //设置被代理的类
        enhancer.setSuperclass(clazz);
        //设置回调
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("befor say");
        //调用被代理类的方法
        proxy.invokeSuper(obj, args);
        System.out.println("after say");
        return null;
    }
}

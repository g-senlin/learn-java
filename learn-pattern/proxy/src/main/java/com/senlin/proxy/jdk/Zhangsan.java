package com.senlin.proxy.jdk;

/**
 * @author gsl
 * @date 2018/9/26 23:56.
 */
public class Zhangsan implements IPerson {

    @Override
    public String say() {
        System.out.println("zhangsan say");
        return null;
    }
}

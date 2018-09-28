package com.senlin.spring.bean.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 *
 *
 * bean 初始化过程回调
 * @author gsl
 * @date 2018/9/28 11:30.
 */
public class InstantiationTracingBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("execute " + beanName + "postProcessBeforeInitialization " + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("execute " + beanName + "postProcessAfterInitialization " + bean);
        return bean;
    }
}

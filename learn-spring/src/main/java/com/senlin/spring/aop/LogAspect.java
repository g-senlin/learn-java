package com.senlin.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * log 记录
 * @author gsl
 * @date 2018/9/28 2:29.
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    //声明切点
    //因为要利用反射机制去读取这个切面中的所有的注解信息
    @Pointcut("execution(* com.senlin.spring.aop..*(..))")
    public void pointcutConfig() {
    }

    @Before("pointcutConfig()")
    public void before(JoinPoint joinPoint) {
        log.info("-------调用方法之前执行------" + joinPoint);
    }

    @After("pointcutConfig()")
    public void after(JoinPoint joinPoint) {
        log.info("-------调用之后执行------" + joinPoint);
    }
    @AfterReturning("pointcutConfig()")
    public void afterReturn(JoinPoint joinPoint) {
        log.info("-------调用获得返回值之后执行------" + joinPoint);
    }

    @AfterThrowing("pointcutConfig()")
    public void afterThrow(JoinPoint joinPoint) {
        log.info("-------抛出异常之后执行------" + joinPoint);
    }
}

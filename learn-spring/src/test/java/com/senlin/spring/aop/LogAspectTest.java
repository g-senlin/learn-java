package com.senlin.spring.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author gsl
 * @date 2018/9/28 2:55.
 */
@ContextConfiguration(locations = {"classpath*:aop.xml"})
@RunWith(SpringRunner.class)
@EnableAspectJAutoProxy
public class LogAspectTest {

    @Autowired
    private UserService userService;
    @Test
    public void testLogAop(){
        userService.login("xx", "xx");

    }
}
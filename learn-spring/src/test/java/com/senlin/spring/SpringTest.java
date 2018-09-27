package com.senlin.spring;

import com.senlin.spring.cycle.UserA;
import com.senlin.spring.cycle.UserAa;
import com.senlin.spring.cycle.UserB;
import com.senlin.spring.cycle.UserBb;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @author gsl
 * @date 2018/9/18 22:50.
 */
public class SpringTest {

    @Test
    public void testSpring() {
        ClassPathResource resource = new ClassPathResource("bean.xml");
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        HelloSpring helloSpring = defaultListableBeanFactory.getBean(HelloSpring.class);
        Assert.assertTrue("hello spring".equals(helloSpring.sayHello("hello spring")));
    }

    @Test
    @Ignore
    public void CycleDependencyWithConstructorArg() {

        ClassPathResource resource = new ClassPathResource("bean-cycle-dependency.xml");
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        UserA userA = defaultListableBeanFactory.getBean(UserA.class);
        UserB userB = defaultListableBeanFactory.getBean(UserB.class);
        Assert.assertTrue("userA".equalsIgnoreCase(userA.getName()));
        Assert.assertTrue("userB".equalsIgnoreCase(userB.getName()));
    }

    @Test
    public void CycleDependencyWithProperty() {
        ClassPathResource resource = new ClassPathResource("bean-cycle-dependency.xml");
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        UserAa userAa = defaultListableBeanFactory.getBean(UserAa.class);
        UserBb userBb = defaultListableBeanFactory.getBean(UserBb.class);
        Assert.assertTrue("userAa".equalsIgnoreCase(userAa.getName()));
        Assert.assertTrue("userBb".equalsIgnoreCase(userBb.getName()));
    }
}

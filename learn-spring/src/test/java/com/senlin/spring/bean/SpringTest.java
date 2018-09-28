package com.senlin.spring.bean;

import com.senlin.spring.bean.cycle.*;
import com.senlin.spring.bean.beanfactorypostprocessor.CacheConfig;
import com.senlin.spring.bean.postprocessor.HelloBeanPostProcessor;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
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
    public void testSpring2() {
        GenericApplicationContext genericApplicationContext = new GenericApplicationContext();
        new XmlBeanDefinitionReader(genericApplicationContext).loadBeanDefinitions("bean.xml");
        genericApplicationContext.refresh();
        HelloSpring helloSpring = genericApplicationContext.getBean(HelloSpring.class);
        Assert.assertTrue("hello spring".equals(helloSpring.sayHello("hello spring")));
    }

    @Test
    public void testSpring3() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean.xml");
        HelloSpring helloSpring = classPathXmlApplicationContext.getBean(HelloSpring.class);
        Assert.assertTrue("hello spring".equals(helloSpring.sayHello("hello spring")));
    }


    @Test
    @Ignore
    public void testCycleDependencyWithConstructorArg() {
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
    public void testCycleDependencyWithProperty() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean-cycle-dependency.xml");
        classPathXmlApplicationContext.setAllowCircularReferences(true);
        UserAa userAa = classPathXmlApplicationContext.getBean(UserAa.class);
        UserBb userBb = classPathXmlApplicationContext.getBean(UserBb.class);

        Assert.assertTrue("userAa".equalsIgnoreCase(userAa.getName()));
        Assert.assertTrue("userBb".equalsIgnoreCase(userBb.getName()));
    }

    @Test
    public void testCycleDependencyWithAnnotation() {
        FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext("classpath:bean-cycle-dependency.xml");
        UserAaa userAaa = fileSystemXmlApplicationContext.getBean(UserAaa.class);
        UserBbb userBbb = fileSystemXmlApplicationContext.getBean(UserBbb.class);

        Assert.assertTrue("userAaa".equalsIgnoreCase(userAaa.getName()));
        Assert.assertTrue("userBbb".equalsIgnoreCase(userBbb.getName()));
    }

    @Test
    public void TestBeanPostProcessor() {
        FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext("classpath:bean-post-processor.xml");
        HelloBeanPostProcessor helloBeanPostProcessor = fileSystemXmlApplicationContext.getBean("helloBeanPostProcessor", HelloBeanPostProcessor.class);
        Assert.assertTrue("hello".equals(helloBeanPostProcessor.say("hello")));
    }

    @Test
    public void TestFacoryBeanPostProcessor() {
        FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext("classpath:bean-post-processor.xml");
        CacheConfig cacheConfig = fileSystemXmlApplicationContext.getBean(CacheConfig.class);
        Assert.assertTrue(CacheConfig.getString("name") != null && CacheConfig.getString("name").equals(CacheConfig.getString("g-senlin")));

    }
}
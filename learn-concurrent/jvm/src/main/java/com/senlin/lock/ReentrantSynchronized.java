package com.senlin.lock;

import sun.applet.Main;

/**
 * @author gsl
 * @date 2018/12/4 21:57.
 */
public class ReentrantSynchronized {

    public static void main(String[] args) {
        new Man().say();
    }

}

class Person{

    public synchronized void say(){
        System.out.println("person say");
        System.out.println("this class:" + this.getClass());

    }
}

class Man extends Person{

    @Override
    public synchronized void say() {
        System.out.println("man say");
        System.out.println("this class:" + this.getClass());
        super.say();
    }
}
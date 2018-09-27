package com.senlin.spring.cycle;

/**
 *  通构造方法注入的循环依赖
 * @author gsl
 * @date 2018/9/27 8:03.
 */
public class UserB {

    private UserA userA;

    public UserB(UserA userA) {
        this.userA = userA;
    }

    public String getName(){
        return "userB";
    }
}

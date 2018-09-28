package com.senlin.spring.bean.cycle;

/**
 *  通构造方法注入的循环依赖
 * @author gsl
 * @date 2018/9/27 8:03.
 */
public class UserA {

    private UserB userB;

    public UserA(UserB userB) {
        this.userB = userB;
    }

    public String getName(){
        return "userA";
    }

}

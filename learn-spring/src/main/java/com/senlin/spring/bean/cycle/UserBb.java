package com.senlin.spring.bean.cycle;

import lombok.Setter;

/**
 * 通过属性的 setter 方法注入的循环依赖
 * @author gsl
 * @date 2018/9/27 8:03.
 */
@Setter
public class UserBb {

    private UserAa userAa;

    public String getName() {
        return "userBb";
    }
}

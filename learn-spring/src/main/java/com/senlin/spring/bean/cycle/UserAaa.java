package com.senlin.spring.bean.cycle;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 通过属性的 setter 方法注入的循环依赖
 * @author gsl
 * @date 2018/9/27 8:03.
 */
@Setter
@Service
public class UserAaa {

    @Autowired
    private UserBbb userBbb;

    public String getName(){
        return "userAaa";
    }

}

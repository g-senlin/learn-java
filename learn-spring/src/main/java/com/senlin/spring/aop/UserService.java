package com.senlin.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author gsl
 * @date 2018/9/28 2:24.
 */
@Service
@Slf4j
public class UserService {

    public boolean login(String username, String password) {
        log.info("username: " + username);
        log.info("password: " + password);
        return "g-senlin".equals(username) && "123456".equals(password);
    }

    public boolean logout(String username) {
        log.info("username: " + username);
        return "g-senlin".equals(username);
    }


}

package com.senlin.commons.util;

import com.senlin.commons.exception.AssertException;
import lombok.experimental.UtilityClass;

/**
 * 断言工具类，直接抛出异常，快速失败
 *
 * @author gsl
 * @date 2018/6/10 13:34.
 */
@UtilityClass
public class AssertUtils {

    /**
     * 判断为 true
     *
     * @param expression 表达式
     */
    public static void assertTrue(boolean expression) {
        assertFalse(!expression);
    }

    /**
     * 判断为 true
     *
     * @param expression 表达式
     * @param msg        异常 msg
     */
    public static void assertTrue(boolean expression, String msg) {
        assertFalse(!expression, msg);
    }

    /**
     * 判断为 false
     *
     * @param expression 表达式
     */
    public static void assertFalse(boolean expression) {
        if (expression) {
            throw new AssertException();
        }
    }

    /**
     * 判断为 false
     *
     * @param expression 表达式
     * @param msg        异常 msg
     */
    public static void assertFalse(boolean expression, String msg) {
        if (expression) {
            throw new AssertException(msg);
        }
    }
}

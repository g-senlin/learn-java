package com.senlin.commons.util;


import org.junit.Test;

/**
 * @author gsl
 * @date 2018/6/10 17:26.
 */
public class AssertUtilsTest {
    @Test
    public void assertTrue() throws Exception {
        AssertUtils.assertTrue(true);
    }

    @Test
    public void assertFalse() throws Exception {
        AssertUtils.assertFalse(false);

    }
}
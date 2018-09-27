package com.senlin.commons.exception;

/**
 * 断言异常
 * @author gsl
 * @date 2018/6/10 14:06.
 */
public class AssertException extends IllegalArgumentException {

    private static final long serialVersionUID = 233600186123362283L;

    public AssertException() {
        super();
    }

    public AssertException(String s) {
        super(s);
    }

    public AssertException(String message, Throwable cause) {
        super(message, cause);
    }

    public AssertException(Throwable cause) {
        super(cause);
    }
}

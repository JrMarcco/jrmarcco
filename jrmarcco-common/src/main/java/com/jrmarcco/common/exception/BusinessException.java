/*
 * Copyright 2017-2018 the original author or authors.
 *
 */
package com.jrmarcco.common.exception;

/**
 * 自定义业务异常。
 *
 * @author jrmarcco
 * @version 1.0  2018/3/28
 */
public class BusinessException extends Exception {
    private static final long serialVersionUID = 3588383826896528532L;

    private IBusinessException exceptionType;

    public BusinessException(IBusinessException exceptionType) {
        this.exceptionType = exceptionType;
    }

    public BusinessException(String msg) {
        super(msg);
    }

    public String getExceptionCode() {
        return exceptionType.getErrorCode();
    }

    public String getExceptionMessage() {
        if (exceptionType != null) {
            return exceptionType.getErrorMessage();
        } else {
            return super.getMessage();
        }
    }
}


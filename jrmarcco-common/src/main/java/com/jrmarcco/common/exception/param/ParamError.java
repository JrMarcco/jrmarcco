/*
 * Copyright 2017-2018 the original author or authors.
 *
 */
package com.jrmarcco.common.exception.param;


import com.jrmarcco.common.exception.IServiceException;

/**
 * 自定义输入参数错误信息。
 *
 * @author jrmarcco
 * @version 1.0  2018/3/28
 */
public enum ParamError implements IServiceException {
    Default("1000", "输入的参数有误");

    private final String code;
    private final String message;

    ParamError(String code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public String getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMessage() {
      return message;
    }
}

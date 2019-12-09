/*
 * Copyright 2017-2018 the original author or authors.
 *
 */
package com.jrmarcco.common.exception;

/**
 * 自定义业务异常接口。
 *
 * @author jrmarcco
 * @version 1.0  2018/3/28
 */
public interface IBusinessException {

    /**
     * 获取业务错误编码。
     */
    String getErrorCode();

    /**
     * 获取业务错误信息。
     */
    String getErrorMessage();
}

/*
 * Copyright 2017-2017 the original author or authors.
 *
 */
package com.jrmarcco.common.base;

import com.jrmarcco.common.constant.BaseConstants;
import com.jrmarcco.common.exception.IServiceException;
import com.jrmarcco.common.exception.ServiceException;
import com.jrmarcco.common.exception.sys.SysError;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回结果对象。
 *
 * @author jrmarcco
 * @version 1.0  2017/12/25
 */
@Data
@AllArgsConstructor
public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = -3947021072109706145L;
    // 状态码："0000"成功，其他为失败
    private String code;

    // 成功为success，其他为失败原因
    private String message;

    // 数据结果
    private T data;

    public BaseResult() {
        this.code = BaseConstants.RESULT_CODE_SUCCESS;
        this.message = BaseConstants.RESULT_MSG_SUCCESS;
    }

    public BaseResult(T data) {
        this.code = BaseConstants.RESULT_CODE_SUCCESS;
        this.message = BaseConstants.RESULT_MSG_SUCCESS;
        this.data = data;
    }

    public BaseResult<T> error() {
        this.code = SysError.Default.getErrorCode();
        this.message = SysError.Default.getErrorMessage();
        return this;
    }

    public BaseResult<T> error(IServiceException err) {
        this.code = err.getErrorCode();
        this.message = err.getErrorMessage();
        return this;
    }

    public BaseResult<T> error(ServiceException e) {
        this.code = e.getExceptionCode();
        this.message = e.getExceptionMessage();
        return this;
    }

    public BaseResult<T> error(String code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }
}

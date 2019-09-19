package com.jrmarcco.common.exception.sys;

import com.jrmarcco.common.exception.IServiceException;

/**
 * 自定义系统异常信息。
 *
 * @author hongjc
 * @version 1.0  2019/1/17
 */
public enum SysError implements IServiceException {
    Default("9999", "服务器异常"),
    TimeOut("9000", "服务器连接超时")
    ;

    private final String code;
    private final String message;

    SysError(String code, String message) {
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
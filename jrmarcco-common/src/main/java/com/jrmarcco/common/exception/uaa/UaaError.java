/**
 * Copyright 2017-2018 the original author or authors.
 */
package com.jrmarcco.common.exception.uaa;

import com.jrmarcco.common.exception.IServiceException;

/**
 * 自定义用户/鉴权错误信息。
 *
 * @author jrmarcco
 * @version 1.0  2018/10/29
 */
public enum UaaError implements IServiceException {
    Default("8000", "用户权限校验失败"),
    InvalidUser("8001", "用户名或密码错误，请重新输入"),
    InvalidToken("8002", "Token无效"),
    PermissionDenied("8003", "访问拒绝，没有权限")
    ;

    private String code;
    private String message;

    UaaError(String code, String message) {
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

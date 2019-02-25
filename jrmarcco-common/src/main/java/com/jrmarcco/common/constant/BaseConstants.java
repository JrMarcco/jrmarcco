/*
 * Copyright 2017-2018 the original author or authors.
 *
 */
package com.jrmarcco.common.constant;

/**
 * 基础常量。
 *
 * @author jrmarcco
 * @version 1.0  2018/2/26
 */
public interface BaseConstants {

    /** 调用成功标识 */
    String RESULT_CODE_SUCCESS = "0000";

    /** 是否已被删除 - 是 */
    Character IS_DELETED_YES = '1';
    /** 是否已被删除 - 否 */
    Character IS_DELETED_NO = '0';

    /** 状态 - 启用 */
    Character STATUS_ENABLE = '1';
    /** 状态 - 禁用 */
    Character STATUS_DISABLE = '0';
}

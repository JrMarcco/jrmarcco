package com.jrmarcco.common.constant;

/**
 * @author hongjc
 * @version 1.0  2019/9/19
 */
public enum StatusEnum {
    ENABLE('1', "启用"),
    DISABLE('0', "禁用");

    private final Character status;
    private final String desc;

    StatusEnum(Character status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}

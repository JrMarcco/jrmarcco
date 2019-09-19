package com.jrmarcco.common.constant;

/**
 * @author hongjc
 * @version 1.0  2019/9/19
 */
public enum DeletedEnum {
    YES('1', "是"),
    NO('0', "否");

    private final Character deleted;
    private final String desc;

    DeletedEnum(Character deleted, String desc) {
        this.deleted = deleted;
        this.desc = desc;
    }
}

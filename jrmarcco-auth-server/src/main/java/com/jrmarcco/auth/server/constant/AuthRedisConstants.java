package com.jrmarcco.auth.server.constant;

import com.jrmarcco.common.constant.RedisConstants;

/**
 * @author hongjc
 * @version 1.0  2019/2/22
 */
public class AuthRedisConstants {

    private static final String PERMISSIONS_PREFIX = "pns:";

    public static String getPermissionKey(String suffix) {
        return RedisConstants.AUTH_PREFIX + PERMISSIONS_PREFIX + suffix;
    }
}

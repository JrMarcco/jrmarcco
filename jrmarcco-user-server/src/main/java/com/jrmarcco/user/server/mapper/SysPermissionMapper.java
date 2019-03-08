package com.jrmarcco.user.server.mapper;

import com.jrmarcco.user.client.entity.SysPermission;

import java.util.Set;

/**
 * @author hongjc
 * @version 1.0  2019/1/29
 */
public interface SysPermissionMapper {

    Set<SysPermission> findByUsername(String username);
}

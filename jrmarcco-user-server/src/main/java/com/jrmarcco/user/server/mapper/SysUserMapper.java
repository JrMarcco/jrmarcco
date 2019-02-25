package com.jrmarcco.user.server.mapper;

import com.jrmarcco.user.client.entity.SysUser;

import java.util.List;

/**
 * @author hongjc
 * @version 1.0  2019/1/11
 */
public interface SysUserMapper {

    SysUser findByUsername(String username);

    List<SysUser> pageSelect(SysUser queryCondition);
}

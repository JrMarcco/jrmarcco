package com.jrmarcco.user.server.service;

import com.jrmarcco.common.base.BaseResult;
import com.jrmarcco.common.base.PageData;
import com.jrmarcco.common.base.PageQueryReq;
import com.jrmarcco.user.client.dto.ValidateUserReq;
import com.jrmarcco.user.client.dto.ValidateUserResp;
import com.jrmarcco.user.client.entity.SysUser;

import java.util.Set;

/**
 * @author hongjc
 * @version 1.0  2019/1/28
 */
public interface IUserService {

    BaseResult<ValidateUserResp> validateUser(ValidateUserReq req);

    BaseResult<Set<String>> getUserPermissions(String username);

    BaseResult<PageData<SysUser>> getUserPage(PageQueryReq<SysUser> req);
}

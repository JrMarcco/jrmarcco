package com.jrmarcco.user.server.controller;

import com.jrmarcco.common.base.BaseResult;
import com.jrmarcco.common.base.PageData;
import com.jrmarcco.common.base.PageQueryReq;
import com.jrmarcco.common.exception.ServiceException;
import com.jrmarcco.user.client.api.UserApi;
import com.jrmarcco.user.client.dto.ValidateUserReq;
import com.jrmarcco.user.client.dto.ValidateUserResp;
import com.jrmarcco.user.client.entity.SysUser;
import com.jrmarcco.user.server.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


/**
 * @author hongjc
 * @version 1.0  2019/1/28
 */
@Api(value = "用户接口", description = "用户信息相关接口（用户校验、用户菜单/权限获取等...）")
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final IUserService userService;

    @ApiOperation(value = "校验用户信息")
    @Override
    public ValidateUserResp validateUser(ValidateUserReq req) throws ServiceException {
        return userService.validateUser(req);
    }

    @ApiOperation(value = "获取用户权限信息")
    @Override
    public Set<String> getUserPermissions(@RequestParam("username") String username) {
        return userService.getUserPermissions(username);
    }

    @Override
    public PageData<SysUser> getUserPage(@RequestBody PageQueryReq<SysUser> req) {
        return userService.getUserPage(req);
    }
}

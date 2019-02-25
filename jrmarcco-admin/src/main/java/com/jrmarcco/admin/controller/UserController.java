package com.jrmarcco.admin.controller;

import com.jrmarcco.admin.remote.UserRemoteApi;
import com.jrmarcco.common.base.BaseResult;
import com.jrmarcco.common.base.PageData;
import com.jrmarcco.common.base.PageQueryReq;
import com.jrmarcco.user.client.entity.SysUser;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hongjc
 * @version 1.0  2019/1/30
 */
@Api(value = "用户信息接口")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRemoteApi userRemoteApi;

    @PostMapping("/page")
    public BaseResult<PageData<SysUser>> page(@RequestBody PageQueryReq<SysUser> req) {
        return userRemoteApi.getUserPage(req);
    }

}

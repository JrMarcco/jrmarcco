package com.jrmarcco.auth.server.controller;

import com.jrmarcco.auth.client.api.JwtApi;
import com.jrmarcco.auth.client.dto.FrontUser;
import com.jrmarcco.auth.client.dto.GetTokenReq;
import com.jrmarcco.auth.client.dto.GetTokenResp;
import com.jrmarcco.auth.client.dto.ValidateTokenReq;
import com.jrmarcco.auth.server.service.IAuthService;
import com.jrmarcco.common.base.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hongjc
 * @version 1.0  2019/1/22
 */
@Api(value = "鉴权api", description = "鉴权相关接口（如：获取token）")
@RestController
@RequiredArgsConstructor
public class JwtController implements JwtApi {

    private final IAuthService authService;

    @ApiOperation(value = "获取accessToken", notes = "登录获取accessToken，同时返回用户基础信息")
    @Override
    public BaseResult<GetTokenResp> getToken(@RequestBody GetTokenReq req) {
        return authService.getToken(req);
    }

    @ApiOperation(value = "校验accessToken", notes = "解析accessToken，判断用户权限")
    @Override
    public BaseResult<Void> validateToken(@RequestBody ValidateTokenReq req) {
        return authService.validateToken(req);
    }
}

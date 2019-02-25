package com.jrmarcco.api.gateway.fallback;

import com.jrmarcco.auth.client.api.JwtApi;
import com.jrmarcco.auth.client.dto.FrontUser;
import com.jrmarcco.auth.client.dto.GetTokenReq;
import com.jrmarcco.auth.client.dto.ValidateTokenReq;
import com.jrmarcco.common.base.BaseResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hongjc
 * @version 1.0  2019/2/18
 */
@Slf4j
public class JwtApiFallback implements JwtApi {
    @Override
    public BaseResult<FrontUser> getToken(GetTokenReq req) {
        log.info("### 调用 {} 异常 ###", "JwtController.getToken()");

        return new BaseResult<FrontUser>().error();
    }

    @Override
    public BaseResult<Void> validateToken(ValidateTokenReq req) {
        log.info("### 调用 {} 异常 ###", "JwtController.validateToken()");
        return new BaseResult<Void>().error();
    }
}

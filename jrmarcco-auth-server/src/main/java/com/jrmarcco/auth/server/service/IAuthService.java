package com.jrmarcco.auth.server.service;

import com.jrmarcco.auth.client.dto.GetTokenReq;
import com.jrmarcco.auth.client.dto.GetTokenResp;
import com.jrmarcco.auth.client.dto.ValidateTokenReq;
import com.jrmarcco.common.base.BaseResult;

/**
 * @author hongjc
 * @version 1.0  2019/1/28
 */
public interface IAuthService {
    BaseResult<GetTokenResp> getToken(GetTokenReq req);

    BaseResult<Void> validateToken(ValidateTokenReq req);
}

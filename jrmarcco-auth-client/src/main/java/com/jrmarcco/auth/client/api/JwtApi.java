package com.jrmarcco.auth.client.api;

import com.jrmarcco.auth.client.dto.GetTokenReq;
import com.jrmarcco.auth.client.dto.ValidateTokenReq;
import com.jrmarcco.common.base.BaseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hongjc
 * @version 1.0  2019/2/18
 */
@RequestMapping("/jwt")
public interface JwtApi {

    @PostMapping("/getToken")
    BaseResult getToken(@RequestBody GetTokenReq req);

    @PostMapping("validateToken")
    BaseResult<Void> validateToken(@RequestBody ValidateTokenReq req);
}

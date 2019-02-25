package com.jrmarcco.auth.client.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hongjc
 * @version 1.0  2019/1/28
 */
@ApiModel(value = "获取token请求信息")
@Data
@NoArgsConstructor
public class GetTokenReq implements Serializable {
    private static final long serialVersionUID = 1465045271273545897L;

    private String username;
    private String password;

    private String clientId;
    private String secretKey;

    private Character type;
}

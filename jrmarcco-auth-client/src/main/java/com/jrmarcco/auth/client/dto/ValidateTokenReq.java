package com.jrmarcco.auth.client.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hongjc
 * @version 1.0  2019/2/18
 */
@ApiModel(value = "校验token请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateTokenReq implements Serializable {
    private static final long serialVersionUID = 1561250761198844334L;

    private String accessToken;
    private String requestUri;
}

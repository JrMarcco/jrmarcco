package com.jrmarcco.auth.client.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hongjc
 * @version 1.0  2019/2/20
 */
@ApiModel(value = "获取token响应信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTokenResp implements Serializable {
    private static final long serialVersionUID = -3098766455962532289L;

    private String accessToken;
    private FrontUser user;
}

/*
 * Copyright 2018-2019 the original author or authors.
 *
 */
package com.jrmarcco.auth.client.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author jrmarcco
 * @version 1.0  2019/2/13
 */
@ApiModel("前端用户数据对象")
@Data
@NoArgsConstructor
public class FrontUser implements Serializable {
    private static final long serialVersionUID = 6583716840970580727L;

    private String username;
    private String nickname;
    private Character gender;
    private String phoneNumber;
    private String avatar;
    private String email;
}

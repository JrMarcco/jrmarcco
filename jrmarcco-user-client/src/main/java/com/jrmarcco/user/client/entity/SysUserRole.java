package com.jrmarcco.user.client.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hongjc
 * @version 1.0  2019/1/28
 */
@ApiModel("用户角色关联信息")
@Data
@NoArgsConstructor
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 4263409795459163699L;

    private String id;
    private Integer userId;
    private Integer roleId;
}

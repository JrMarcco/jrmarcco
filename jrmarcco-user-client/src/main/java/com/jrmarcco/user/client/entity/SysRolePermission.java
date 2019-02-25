package com.jrmarcco.user.client.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hongjc
 * @version 1.0  2019/1/28
 */
@ApiModel("角色权限关联信息")
@Data
@NoArgsConstructor
public class SysRolePermission implements Serializable {
    private static final long serialVersionUID = -7803911989280783561L;

    private String id;
    private Integer roleId;
    private Integer permissionId;
}

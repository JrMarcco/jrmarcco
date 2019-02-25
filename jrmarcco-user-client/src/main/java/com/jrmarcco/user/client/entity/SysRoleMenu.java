package com.jrmarcco.user.client.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hongjc
 * @version 1.0  2019/1/28
 */
@ApiModel("角色菜单关联信息")
@Data
@NoArgsConstructor
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = 7467767732508761533L;

    private String id;
    private Integer roleId;
    private Integer menuId;
}

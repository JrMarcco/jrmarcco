package com.jrmarcco.user.client.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hongjc
 * @version 1.0  2019/1/28
 */
@ApiModel("系统菜单信息")
@Data
@NoArgsConstructor
public class SysMenu implements Serializable {
    private static final long serialVersionUID = -5147033208638452842L;

    private Integer id;
    private Integer parentId;
    private String name;
    private String url;
    private String orderNumber;
}

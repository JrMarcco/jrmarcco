package com.jrmarcco.user.client.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hongjc
 * @version 1.0  2019/1/28
 */
@ApiModel("系统授权信息")
@Data
@NoArgsConstructor
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 5359916804095843556L;

    private Integer id;
    private String name;
    private String url;
    private Character type;
    private Integer menuId;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date updateTime;
}

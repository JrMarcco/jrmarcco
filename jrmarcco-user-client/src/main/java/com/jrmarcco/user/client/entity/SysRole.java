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
@ApiModel("系统角色信息")
@Data
@NoArgsConstructor
public class SysRole implements Serializable {
    private static final long serialVersionUID = 252970060348737771L;

    private Integer id;
    private String code;
    private String name;
    private String desc;
    private Character type;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date updateTime;
}

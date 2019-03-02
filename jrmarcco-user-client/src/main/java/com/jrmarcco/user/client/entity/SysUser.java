package com.jrmarcco.user.client.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hongjc
 * @version 1.0  2019/1/11
 */
@ApiModel("系统用户信息")
@Data
@NoArgsConstructor
public class SysUser implements Serializable {
    private static final long serialVersionUID = -6435413927307702642L;

    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private Character gender;
    private String phoneNumber;
    private String avatar;
    private String email;
    private Integer roleId;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date updateTime;
    private Character isDeleted;
}

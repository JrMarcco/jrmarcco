package com.jrmarcco.user.client.dto;

import com.jrmarcco.user.client.entity.SysUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * @author hongjc
 * @version 1.0  2019/1/29
 */
@Data
@NoArgsConstructor
public class ValidateUserResp implements Serializable {
    private static final long serialVersionUID = -2839138629267152060L;

    private SysUser user;
    private Set<String> permissions;

    public ValidateUserResp(SysUser user) {
        this.user = user;
    }
}

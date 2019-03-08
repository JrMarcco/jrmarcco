package com.jrmarcco.auth.server.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hongjc
 * @version 1.0  2019/3/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtInfo implements Serializable {
    private static final long serialVersionUID = 4860411019854120112L;

    private String username;
    private Integer roleId;
}

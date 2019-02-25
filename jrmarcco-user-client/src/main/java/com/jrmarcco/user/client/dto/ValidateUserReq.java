package com.jrmarcco.user.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hongjc
 * @version 1.0  2019/1/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateUserReq implements Serializable {
    private static final long serialVersionUID = -4169939117143945041L;

    private String username;
    private String password;
}

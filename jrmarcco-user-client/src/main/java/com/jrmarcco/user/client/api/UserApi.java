package com.jrmarcco.user.client.api;

import com.jrmarcco.common.base.PageData;
import com.jrmarcco.common.base.PageQueryReq;
import com.jrmarcco.common.exception.ServiceException;
import com.jrmarcco.user.client.dto.ValidateUserReq;
import com.jrmarcco.user.client.dto.ValidateUserResp;
import com.jrmarcco.user.client.entity.SysUser;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author hongjc
 * @version 1.0  2019/1/28
 */
@RequestMapping("/user")
public interface UserApi {

    @PostMapping("/validate")
    ValidateUserResp validateUser(@RequestBody ValidateUserReq req) throws ServiceException;

    @GetMapping("/getUserPermissions")
    Set<String> getUserPermissions(@RequestParam("username") String username);

    @PostMapping("/getUserPage")
    PageData<SysUser> getUserPage(@RequestBody PageQueryReq<SysUser> req);
}

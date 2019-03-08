package com.jrmarcco.user.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jrmarcco.common.base.BaseResult;
import com.jrmarcco.common.base.PageData;
import com.jrmarcco.common.base.PageQueryReq;
import com.jrmarcco.common.exception.uaa.UaaError;
import com.jrmarcco.user.client.dto.ValidateUserReq;
import com.jrmarcco.user.client.dto.ValidateUserResp;
import com.jrmarcco.user.client.entity.SysPermission;
import com.jrmarcco.user.client.entity.SysUser;
import com.jrmarcco.user.server.mapper.SysPermissionMapper;
import com.jrmarcco.user.server.mapper.SysUserMapper;
import com.jrmarcco.user.server.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author hongjc
 * @version 1.0  2019/1/28
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final SysUserMapper userMapper;
    private final SysPermissionMapper permissionMapper;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public BaseResult<ValidateUserResp> validateUser(ValidateUserReq req) {
        var result = new BaseResult<ValidateUserResp>();

        // 校验用户名密码
        var user = userMapper.findByUsername(req.getUsername());
        if (user == null || !encoder.matches(req.getPassword(), user.getPassword())) {
            result.error(UaaError.InvalidUser);
        } else {
            var validateUserResp = new ValidateUserResp(user);

            // 获取权限信息
            var permissions = getPermissions(user.getUsername());
            if (!CollectionUtils.isEmpty(permissions)) {
                validateUserResp.setPermissions(permissions);
            }

            result.setData(validateUserResp);
        }

        return result;
    }

    @Override
    public BaseResult<Set<String>> getUserPermissions(String username) {
        return new BaseResult<>(getPermissions(username));
    }

    @Override
    public BaseResult<PageData<SysUser>> getUserPage(PageQueryReq<SysUser> req) {
        PageHelper.startPage(req.getPageIndex(), req.getPageSize());
        var page = new PageInfo<>(userMapper.pageSelect(req.getQueryCondition()));

        return new BaseResult<>(new PageData<>(page.getList(), page.getTotal()));
    }

    // ====================================================================================================
    //                                   Private Method
    // ====================================================================================================
    private Set<String> getPermissions(String username) {
        return Optional.ofNullable(permissionMapper.findByUsername(username))
                .orElse(Collections.emptySet())
                .stream()
                .map(SysPermission::getUrl)
                .collect(Collectors.toSet());
    }
}

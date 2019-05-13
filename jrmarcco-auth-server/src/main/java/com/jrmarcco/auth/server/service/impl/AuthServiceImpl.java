package com.jrmarcco.auth.server.service.impl;

import com.jrmarcco.auth.client.dto.FrontUser;
import com.jrmarcco.auth.client.dto.GetTokenReq;
import com.jrmarcco.auth.client.dto.GetTokenResp;
import com.jrmarcco.auth.client.dto.ValidateTokenReq;
import com.jrmarcco.auth.server.bean.JwtInfo;
import com.jrmarcco.auth.server.constant.AuthRedisConstants;
import com.jrmarcco.auth.server.key.JwtKey;
import com.jrmarcco.auth.server.remote.UserRemoteApi;
import com.jrmarcco.auth.server.service.IAuthService;
import com.jrmarcco.auth.server.util.JwtUtils;
import com.jrmarcco.common.base.BaseResult;
import com.jrmarcco.common.constant.BaseConstants;
import com.jrmarcco.common.exception.ServiceException;
import com.jrmarcco.common.exception.uaa.UaaError;
import com.jrmarcco.common.util.RedisUtils;
import com.jrmarcco.user.client.dto.ValidateUserReq;
import com.jrmarcco.user.client.dto.ValidateUserResp;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * @author hongjc
 * @version 1.0  2019/1/28
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    @Value("${jrmarcco.jwt.expire:30}")
    private int expire;

    private final StringRedisTemplate rt;
    private final JwtKey jwtKey;

    private final UserRemoteApi userRemoteApi;

    @Override
    public BaseResult<GetTokenResp> getToken(GetTokenReq req) {
        var result = new BaseResult<GetTokenResp>();

        var validateUserReq = new ValidateUserReq(req.getUsername(), req.getPassword());

        try {
            var validateUserResp = userRemoteApi.validateUser(validateUserReq);
            var user = validateUserResp.getUser();

            // 缓存用户权限信息
            var permissions = validateUserResp.getPermissions();
            if (permissions != null) {
                var key = AuthRedisConstants.getPermissionKey(String.valueOf(user.getRoleId()));
                RedisUtils.setStringValue(rt, key, permissions);
            }

            var frontUser = new FrontUser();
            BeanUtils.copyProperties(user, frontUser);
            result.setData(new GetTokenResp(
                    JwtUtils.generateToken(user.getUsername(), user.getRoleId(), jwtKey.getPrivateKey(), expire),
                    frontUser
            ));
        } catch (ServiceException e) {
            result.error(e);
        }

        return result;
    }

    @Override
    public BaseResult<Void> validateToken(ValidateTokenReq req) {
        var result = new BaseResult<Void>();
        try {
            var jwtInfo = JwtUtils.parseToken(req.getAccessToken(), jwtKey.getPublicKey());
            if (StringUtils.isEmpty(jwtInfo.getUsername())) {
                result.error(UaaError.InvalidToken);
            } else {
                var permissions = getUserPermissions(jwtInfo);
                if (!hasPermission(permissions, req.getRequestUri())) {
                    result.error(UaaError.PermissionDenied);
                }
            }
        } catch (ExpiredJwtException e) {
            result.error(UaaError.InvalidToken);
        }
        return result;
    }

    // ====================================================================================================
    //                                   Private Method
    // ====================================================================================================
    @SuppressWarnings("unchecked")
    private Set<String> getUserPermissions(JwtInfo jwtInfo) {
        var key = AuthRedisConstants.getPermissionKey(String.valueOf(jwtInfo.getRoleId()));
        var permissions = RedisUtils.getStringValue(rt, Set.class, key);
        if (permissions == null) {
            permissions = userRemoteApi.getUserPermissions(jwtInfo.getUsername());

            // 缓存
            if (permissions != null) {
                return RedisUtils.setStringValue(rt, key, permissions);
            }
        }
        return permissions;
    }

    private boolean hasPermission(Set<String> permissions, String requestUri) {
        if (CollectionUtils.isEmpty(permissions)) {
            return false;
        }

        if (permissions.contains("*")) {
            return true;
        }

        // 去除网关前缀
        if (requestUri.startsWith("/")) {
            requestUri = requestUri.replaceFirst("/", "");
        }
        var resourceUrl = requestUri.substring(requestUri.indexOf('/'));
        return permissions.contains(resourceUrl);
    }
}

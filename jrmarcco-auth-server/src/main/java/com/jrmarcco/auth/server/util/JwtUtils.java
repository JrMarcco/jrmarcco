package com.jrmarcco.auth.server.util;

import com.jrmarcco.auth.server.bean.JwtInfo;
import com.jrmarcco.common.util.DateUtils;
import io.jsonwebtoken.Jwts;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDateTime;

/**
 * @author hongjc
 * @version 1.0  2019/1/22
 */
public class JwtUtils {

    private static final String ROLE_ID = "roleId";

    public static String generateToken(String username, Integer roleId, PrivateKey privateKey, int expire) {
        var expireTime = LocalDateTime.now().plusMinutes(expire);

        return Jwts.builder()
                .setSubject(username)
                .setHeaderParam(ROLE_ID, roleId)
                .setExpiration(DateUtils.toDate(expireTime))
                .signWith(privateKey)
                .compact();
    }

    public static JwtInfo parseToken(String accessToken, PublicKey publicKey) {
        var jwt = Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(accessToken);

        return new JwtInfo(
                jwt.getBody().getSubject(),
                (Integer) jwt.getHeader().get(ROLE_ID)
        );
    }
}

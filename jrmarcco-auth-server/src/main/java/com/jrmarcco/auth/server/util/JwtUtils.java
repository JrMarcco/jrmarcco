package com.jrmarcco.auth.server.util;

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

    public static String generateToken(String username, PrivateKey privateKey, int expire) {
        var expireTime = LocalDateTime.now().plusMinutes(expire);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(DateUtils.toDate(expireTime))
                .signWith(privateKey)
                .compact();
    }

    public static String parseToken(String accessToken, PublicKey publicKey) {
        return Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(accessToken)
                .getBody()
                .getSubject();
    }
}

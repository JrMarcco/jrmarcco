package com.jrmarcco.auth.server.key;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author hongjc
 * @version 1.0  2019/1/29
 */
@Data
@NoArgsConstructor
public class JwtKey {
    private PublicKey publicKey;
    private PrivateKey privateKey;
}

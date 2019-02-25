package com.jrmarcco.auth.server.config;

import com.jrmarcco.auth.server.key.JwtKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author hongjc
 * @version 1.0  2019/1/29
 */
@Slf4j
@Configuration
public class JwtKeyConfiguration {

    @Bean
    public JwtKey jwtKey() throws Exception {
        var jwtKey = new JwtKey();

        try {
            // 读取公钥信息
            var publicKeyPem = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("jwt_public_key.pem").toURI())));
            publicKeyPem = publicKeyPem
                    .replaceAll("\\n", "")
                    .replaceAll("\\r", "")
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "");

            var keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyPem));
            jwtKey.setPublicKey(KeyFactory.getInstance("RSA").generatePublic(keySpec));
        } catch (Exception e) {
            log.error("### 读取jwt公钥失败：{} ###", e.getMessage(), e);
            throw e;
        }

        try {
            // 读取密钥信息
            var privateKeyPem = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("jwt_private_key.pem").toURI())));
            privateKeyPem = privateKeyPem
                    .replaceAll("\\n", "")
                    .replaceAll("\\r", "")
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "");

            var keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyPem));
            jwtKey.setPrivateKey(KeyFactory.getInstance("RSA").generatePrivate(keySpec));
        } catch (Exception e) {
            log.error("### 读取jwt秘钥失败：{} ###", e.getMessage(), e);
            throw e;
        }

        return jwtKey;
    }
}

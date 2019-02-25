package com.jrmarcco.api.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author hongjc
 * @version 1.0  2019/1/22
 */
@SuppressWarnings("ConfigurationProperties")
@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "jrmarcco.gateway")
public class GatewayConfiguration {

    private String ignorePaths;

    private String tokenHeader;
}

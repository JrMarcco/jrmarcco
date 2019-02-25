package com.jrmarcco.api.gateway;

import com.jrmarcco.api.gateway.config.ConfigPackage;
import com.jrmarcco.api.gateway.filter.FilterPackage;
import com.jrmarcco.api.gateway.remote.RemotePackage;
import com.jrmarcco.common.config.global.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hongjc
 * @version 1.0  2019/1/12
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = {RemotePackage.class})
@ComponentScan(basePackageClasses = {ConfigPackage.class, FilterPackage.class, GlobalExceptionHandler.class})
public class ApiGatewayStarter {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayStarter.class, args);

        log.info("### Api Gateway Has Already Started ###");
    }
}

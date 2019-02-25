package com.jrmarcco.auth.server;

import com.jrmarcco.auth.server.config.ConfigPackage;
import com.jrmarcco.auth.server.controller.ControllerPackage;
import com.jrmarcco.auth.server.mapper.MapperPackage;
import com.jrmarcco.auth.server.service.impl.ServiceImplPackage;
import com.jrmarcco.common.config.global.GlobalExceptionHandler;
import com.jrmarcco.common.config.redis.RedisConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hongjc
 * @version 1.0  2019/1/10
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackageClasses = {ConfigPackage.class, ControllerPackage.class, ServiceImplPackage.class, GlobalExceptionHandler.class, RedisConfiguration.class})
@MapperScan(basePackageClasses = {MapperPackage.class})
public class AuthServerStarter {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerStarter.class, args);

        log.info("### Auth Server Has Already Started ###");
    }
}

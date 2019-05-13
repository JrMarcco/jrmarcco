package com.jrmarcco.user.server;

import com.jrmarcco.common.config.global.GlobalExceptionHandler;
import com.jrmarcco.user.server.config.ConfigPackage;
import com.jrmarcco.user.server.controller.ControllerPackage;
import com.jrmarcco.user.server.mapper.MapperPackage;
import com.jrmarcco.user.server.service.impl.ServiceImplPackage;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hongjc
 * @version 1.0  2019/1/28
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackageClasses = {ConfigPackage.class, ControllerPackage.class, ServiceImplPackage.class})
@MapperScan(basePackageClasses = {MapperPackage.class})
public class UserServerStarter {

    public static void main(String[] args) {
        SpringApplication.run(UserServerStarter.class, args);

        log.info("### User Server Has Already Started ###");
    }
}

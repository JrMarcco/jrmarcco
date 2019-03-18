package com.jrmarcco.admin;

import com.jrmarcco.admin.config.ConfigPackage;
import com.jrmarcco.admin.controller.ControllerPackage;
import com.jrmarcco.common.config.global.GlobalExceptionHandler;
import com.jrmarcco.common.interceptor.ControllerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hongjc
 * @version 1.0  2019/1/28
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackageClasses = {ConfigPackage.class, ControllerPackage.class, GlobalExceptionHandler.class,
        ControllerInterceptor.class})
public class AdminStarter {

    public static void main(String[] args) {
        SpringApplication.run(AdminStarter.class, args);

        log.info("### Admin Has Already Started ###");
    }
}

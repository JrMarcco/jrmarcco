package com.jrmarcco.config.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author hongjc
 * @version 1.0  2019/1/10
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigServerStarter {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerStarter.class, args);

        log.info("### Config Server Has Already Started ###");
    }
}

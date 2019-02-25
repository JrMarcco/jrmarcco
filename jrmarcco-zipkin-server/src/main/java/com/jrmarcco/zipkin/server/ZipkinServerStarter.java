package com.jrmarcco.zipkin.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hongjc
 * @version 1.0  2019/1/17
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class ZipkinServerStarter {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerStarter.class, args);

        log.info("### Zipkin Server Has Already Started ###");
    }
}

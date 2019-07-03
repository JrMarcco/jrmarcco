package com.jrmarcco.mqtt.server;

import com.jrmarcco.common.config.redis.RedisConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hongjc
 * @version 1.0  2019/7/3
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackageClasses = {RedisConfiguration.class})
public class MqttServerStarter {

    public static void main(String[] args) {
        SpringApplication.run(MqttServerStarter.class, args);

        log.info("### MQTT Server Has Already Started ###");
    }
}

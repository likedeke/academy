package com.like.academy.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author likeLove
 * @time 2020-08-20  20:52
 */
@SpringBootApplication
@ComponentScan ("com.like.academy")
@EnableDiscoveryClient // 开启服务发现
@EnableFeignClients // 开启服务调用
@RefreshScope
public class ServiceEduApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceEduApplication.class, args);
    }
}

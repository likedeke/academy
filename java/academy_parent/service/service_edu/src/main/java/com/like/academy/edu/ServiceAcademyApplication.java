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
// 开启服务发现
@EnableDiscoveryClient
// 开启服务调用
@EnableFeignClients
@RefreshScope
public class ServiceAcademyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAcademyApplication.class, args);
    }
}

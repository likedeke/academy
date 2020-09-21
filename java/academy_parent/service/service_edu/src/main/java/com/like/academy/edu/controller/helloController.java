package com.like.academy.edu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author likeLove
 * @time 2020-08-28  20:32
 */
@RestController
@RefreshScope
public class helloController {
    @Value("nacos")
    private String myName;
    @GetMapping ("/hello")
    public String hello() {
        return "你好"+ myName;
    }
}

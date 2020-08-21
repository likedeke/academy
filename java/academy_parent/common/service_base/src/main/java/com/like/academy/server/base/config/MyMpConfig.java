package com.like.academy.server.base.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author likeLove
 * @time 2020-08-20  18:49
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.like.academy.edu.mapper")
public class MyMpConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

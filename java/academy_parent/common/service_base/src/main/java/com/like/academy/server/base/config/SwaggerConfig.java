package com.like.academy.server.base.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author likeLove
 * @time 2020-08-20  21:28
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    //网站api测试
    public Docket webApiConfig() {
        return  new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select().paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();
    }

    @Bean
    //管理员api测试
    public Docket adminApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select().paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();
    }
    //针对webApi的文件解析
    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("网站前台Api文档")
                .description("本文档是关于学院的Api接口定义")
                .version("0.1")
                .contact(new Contact("like","https://github.com/likedeke","980650920@qq.com"))
                .build();
    }
    //针对adminApi的解析
    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("后台管理系统Api文档")
                .description("本文档是关于学院的后台Api接口定义")
                .version("0.1")
                .contact(new Contact("like"
                        , "https://github.com/likedeke"
                        , "980650920@qq.com"))
                .build();
    }

}

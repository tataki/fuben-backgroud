package com.foo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    // http://127.0.0.1:8088/swagger-ui.html 原路径
    // http://127.0.0.1:8088/doc.html
    // 配置swagger2 核心配置 docket
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)//指定为swagger2
                .apiInfo(apiInfo())//定义api文档汇总
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.foo.controller")) //指定controller包
                .paths(PathSelectors.any()) //所有controller
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("副本 接口API") //文档页标题
                .contact(new Contact("vycz","fuben.pro","492241408@qq.com"))
                .description("为副本网站提供API文档")
                .version("1.0.1")
                .termsOfServiceUrl("http://fuben.pro")
                .build();
    }
}

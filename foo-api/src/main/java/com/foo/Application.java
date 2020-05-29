package com.foo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
// 扫描mybatis通用mapper
@MapperScan(basePackages = "com.foo.mapper")
// 扫描所有包及相关组件包
@EnableScheduling //开启定时任务
@EnableRedisHttpSession // 开启基于redis的http session
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}

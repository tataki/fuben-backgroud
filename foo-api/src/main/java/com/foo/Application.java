package com.foo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.foo.mapper")
@EnableScheduling //开启定时任务
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}

package com.foo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    public CorsConfig() {
    }
    @Bean
    public CorsFilter corsFilter(){
        //1.添加cors配置信息
        CorsConfiguration configuration = new CorsConfiguration();
        //设置跨域域名
        configuration.addAllowedOrigin("*");
        //设置是否发送cookie信息
        configuration.setAllowCredentials(true);
        //设置允许请求方式
        configuration.addAllowedMethod("*");
        //设置允许header
        configuration.addAllowedHeader("*");

        //2.为url添加映射路径
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**",configuration);

        //3.返回重新定义好的source
        return new CorsFilter(corsConfigurationSource);
    }
}

package com.foo.config;

import com.foo.common.config.PreReadUploadConfig;
import com.foo.intercepter.UserTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private PreReadUploadConfig uploadConfig;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/META-INF/resource/") //映射swagger2
                .addResourceLocations("file:///"+uploadConfig.getUploadPath());//映射本地资源
    }
    @Bean
    public UserTokenInterceptor userTokenInterceptor(){
            return new UserTokenInterceptor();
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userTokenInterceptor())
                .addPathPatterns("/hello");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

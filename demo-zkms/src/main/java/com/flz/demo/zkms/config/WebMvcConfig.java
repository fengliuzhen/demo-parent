package com.flz.demo.zkms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 注册 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(beforeAddInterceptor()).addPathPatterns("/main/**");
    }

    /**
     * 先注入自定义的拦截器
     * flz 2018-05-15
     */
    @Bean
    public AuthInterceptor beforeAddInterceptor() {
        return new AuthInterceptor();
    }
}

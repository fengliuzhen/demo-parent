package com.flz.demo.filter;

import org.apache.catalina.filters.RemoteIpFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
    //日志
    Logger logger = LoggerFactory.getLogger(FilterConfiguration.class);

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new FilterRoute());//添加过滤器
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        registration.addInitParameter("params","def");//添加默认参数
        registration.setName("FilterRoute");//设置优先级
        registration.setOrder(1);//设置优先级
        return registration;
    }
}

package com.flz.demo;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloServiceConfig {
    /**
     *将服务检查策略改为PingUrl
     * @return
     */
    @Bean
    public IPing ribbonPing(){
        return new PingUrl();
    }
    @Bean
    public IRule ribbonRule() {
        return new WeightedResponseTimeRule();//这里配置策略，和配置文件对应
    }
}

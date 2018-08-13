package com.flz.demo.ribbon.config;

import com.flz.demo.HelloServiceConfig;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClient(name = "sp-producer",configuration = HelloServiceConfig.class)
public class RibbonConfig {
}

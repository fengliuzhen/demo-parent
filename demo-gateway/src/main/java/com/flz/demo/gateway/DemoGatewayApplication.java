package com.flz.demo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class DemoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoGatewayApplication.class, args);
    }
}

package com.flz.demo.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DemoProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProducerApplication.class, args);
    }
}

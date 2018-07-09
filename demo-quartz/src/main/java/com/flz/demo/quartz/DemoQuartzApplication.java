package com.flz.demo.quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.flz.demo")
@MapperScan("com.flz.demo.dao")
public class DemoQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoQuartzApplication.class, args);
    }
}

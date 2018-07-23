package com.flz.demo.config.controller;

import com.flz.demo.config.entity.JdbcProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
public class TestController {

    @Autowired
    private JdbcProperty jdbcProperty;

    @RequestMapping("/test")
    public String getTest()
    {
        return jdbcProperty.getUrl();
    }
}

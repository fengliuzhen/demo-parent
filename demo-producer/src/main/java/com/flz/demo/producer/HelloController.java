package com.flz.demo.producer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String Hello(String name)
    {
        return "producer:"+name;
    }
}

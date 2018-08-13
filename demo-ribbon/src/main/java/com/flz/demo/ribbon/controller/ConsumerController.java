package com.flz.demo.ribbon.controller;

import com.flz.demo.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/ribbon-consumer",method =RequestMethod.GET)
    public String helloConsumer(String name)
    {
        return helloService.helloService(name);
    }
}

package com.flz.demo.producer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/test")
    @HystrixCommand(fallbackMethod = "testError")
    public String Hello(String name)
    {
        return "producertest:"+name;
    }
    public String testError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}

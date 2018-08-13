package com.flz.demo.test.controller;

import com.flz.demo.test.service.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class index {

    @RequestMapping("/test")
    public String test()
    {
        //单例模式
        Singleton singleton=Singleton.getInstance();
        String message=singleton.getMessage();
        //抽象工厂模式
        IPcFactory factory=new DellFactory();
        IMouse mouse=factory.createMouse();
        //抽象类
        AbstractMouse abstractMouse=factory.createAbstractMouse();
        String doubbleMsg=abstractMouse.doubbleClick();
        return mouse.sayHi()+message+doubbleMsg;
    }
}

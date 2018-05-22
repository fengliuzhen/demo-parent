package com.flz.demo.controller;

import com.flz.demo.common.DateUtils;
import com.flz.demo.entity.UserEntity;
import com.flz.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String Login(Model model)
    {
        UserEntity userEntity=userService.getUserEntity(1);
        String adminName="未知";
        if(!Objects.equals(userEntity,null))
        {
            adminName=userEntity.getRealName();
        }
        model.addAttribute("systemName","后台管理系统");
        model.addAttribute("cureentTime", DateUtils.getNowTime());
        model.addAttribute("adminName",adminName);
        return "login";
    }
}

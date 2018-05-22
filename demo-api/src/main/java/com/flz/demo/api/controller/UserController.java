package com.flz.demo.api.controller;

import com.flz.demo.entity.UserEntity;
import com.flz.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value ="/getuserinfo/{uid}",method = RequestMethod.GET)
    public UserEntity getUserInfo(@PathVariable("uid")int uid)
    {
        UserEntity userEntity=userService.getUserEntity(uid);
        return userEntity;
    }
}

package com.flz.demo.service.impl;

import com.flz.demo.dao.UserDao;
import com.flz.demo.entity.UserEntity;
import com.flz.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity getUserEntity(int userId)
    {
        return userDao.getUserEntity(userId);
    }
}

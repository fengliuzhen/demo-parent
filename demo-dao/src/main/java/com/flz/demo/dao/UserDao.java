package com.flz.demo.dao;

import org.apache.ibatis.annotations.Param;
import com.flz.demo.entity.UserEntity;

public interface UserDao {
    UserEntity getUserEntity(@Param("userId") int userId);
}

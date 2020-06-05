package com.myssm.service.impl;

import com.myssm.dao.UserMapper;
import com.myssm.pojo.User;
import com.myssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: TJC
 * @Date: 2020/6/5 15:31
 * @description: TODO
 */
//@Service("userService")
public class UserServiceImpl implements UserService {

//    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }
}
package com.myssm.service;

import com.myssm.pojo.User;

/**
 * @Author: TJC
 * @Date: 2020/6/5 15:16
 * @description: TODO
 */
public interface UserService {
    // 根据username查询用户
    User findUserByName(String username);
}

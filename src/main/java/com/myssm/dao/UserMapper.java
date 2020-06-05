package com.myssm.dao;

import com.myssm.pojo.User;

/**
 * @Author: TJC
 * @Date: 2020/6/5 14:09
 * @description: TODO
 */
public interface UserMapper {

    // 根据username查询用户
    User findUserByName(String username);
}

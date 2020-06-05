package com.myssm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: TJC
 * @Date: 2020/6/5 14:07
 * @description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userId;
    private String username;
    private String password;
}

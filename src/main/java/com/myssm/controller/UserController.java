package com.myssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: TJC
 * @Date: 2020/6/5 15:57
 * @description: TODO
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login() {
        return null;
    }

}

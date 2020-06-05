package com.myssm.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: TJC
 * @Date: 2020/6/5 15:55
 * @description: TODO
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        if (request.getRequestURI().contains("toLogin")) {
            return true;
        }

        if (request.getRequestURI().contains("login")) {
            return true;
        }

        if (session.getAttribute("userLoginInfo") != null) {
            return true;
        }

        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        return false;
    }
}

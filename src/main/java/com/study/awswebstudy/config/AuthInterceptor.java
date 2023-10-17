package com.study.awswebstudy.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


//@Component
public class AuthInterceptor implements HandlerInterceptor {


    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession();

        if(session.getAttribute("login") != null) {

            logger.info("current user is not logined");

            // 로그인하지 않은 사용자일 경우 로그인 페이지로 이동
            response.sendRedirect("/user/login");
            return false;
        }
        logger.info("current user is logined");

        // 로그인한 사용자일 경우 Controller 호출
        return true;
    }
}
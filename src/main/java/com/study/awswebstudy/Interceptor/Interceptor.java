package com.study.awswebstudy.Interceptor;

import com.study.awswebstudy.service.posts.AccessLogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class Interceptor implements HandlerInterceptor {

    private final AccessLogService accessLogService;

    @Override
    public boolean preHandler(HttpServletRequest req, HttpServletResponse res, Object handler){
        return true;
    }



}

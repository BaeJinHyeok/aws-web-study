package com.study.awswebstudy.config.auth;

import com.study.awswebstudy.Interceptor.Interceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@RequiredArgsConstructor1
public class InterceptorConfig {

    private final accessLogservice
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new Interceptor(accessLogService)).addPathPatterns("/**")
    }
}

package com.study.awswebstudy.Interceptor;

import com.study.awswebstudy.service.posts.AccessLogService;
import com.study.awswebstudy.web.dto.AccessLogDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;

@RequiredArgsConstructor
public class Interceptor implements HandlerInterceptor {

    private final AccessLogService accessLogService;

    @Override
    public boolean preHandler(HttpServletRequest req, HttpServletResponse res, Object handler){
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String requestUri = request.getRequestURI();


        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            Class<?> clazz = method.getDeclaringClass();

            String className = clazz.getName();
            String classSimpleName = clazz.getSimpleName();
            String methodName = method.getName();

            AccessLogDto accessLog = new AccessLogDto();

            accessLog.setRequestUri(requestUri);

            accessLog.setClassName(className);
            accessLog.setClassSimpleName(classSimpleName);
            accessLog.setMethodName(methodName);

            accessLogService.register(accessLog);
        } else {
            System.out.println("Handler" + handler);
        }

    }


}

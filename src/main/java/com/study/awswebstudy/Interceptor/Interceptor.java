package com.study.awswebstudy.Interceptor;

import com.study.awswebstudy.Util.Cookies;
import com.study.awswebstudy.service.posts.AccessLogService;
import com.study.awswebstudy.web.dto.AccessLogDto;
import com.study.awswebstudy.web.dto.CookieDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public class Interceptor implements HandlerInterceptor {

    private final AccessLogService accessLogService;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler){




        Cookies cookiesUt = new Cookies(req);


        String nm = "test";
        String value;
        Cookie[] cookies = req.getCookies();
        if(cookies != null) { //가져온 쿠키값이 있으면

            for (Cookie cookie : cookies) {   //쿠키 값을 순회해서 있는 값인지 판단.
                if (nm.equals(cookie)) { // 있으면
                    //code
                    String cookName = cookie.getName();
                    String cookValue = cookie.getValue();
                    System.out.println(cookName);
                    System.out.println(cookValue);
                }
                else{ // 쿠키값을 못찾았으면 쿠키 생성
                    try {
                        cookiesUt.createCookie("test","1","domain", "/", 3600);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        else{ // 쿠키 값이 없으면 생성
            try {
                cookiesUt.createCookie("test","1","domain", "/", 3600);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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

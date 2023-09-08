//package com.study.awswebstudy.config.auth;
//
//import com.study.awswebstudy.config.auth.dto.LoginUser;
//import com.study.awswebstudy.config.auth.dto.SessionUser;
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//import org.hibernate.Session;
//import org.hibernate.annotations.Comment;
//import org.springframework.core.MethodParameter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
//@RequiredArgsConstructor
//@Component
//public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
//
//    private final HttpSession httpSession;
//
//    @Override
//    public boolean supportsParameter(MethodParameter parameter){
//
//        boolean isLoginUserAnnotation = parameter.getMethodAnnotation(LoginUser.class) != null;
//
//        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
//
//        return isLoginUserAnnotation && isUserClass;
//    }
//
//    @Override
//    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
//                                 NativeWebRequest webRequest, WebDataBinderFactory binderFactory)
//        throws Exception{
//        System.out.println(httpSession.getAttribute("user"+">>>>>>>>>>>>>>>>ZZZZZZZZLoginUserArgumentResolver"));
//        return httpSession.getAttribute("user");
//    }
//
//}

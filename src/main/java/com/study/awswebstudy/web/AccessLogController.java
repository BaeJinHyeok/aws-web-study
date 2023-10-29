package com.study.awswebstudy.web;

import com.study.awswebstudy.web.dto.AccessLogDto;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.http.HttpRequest;
import java.util.Map;

public class AccessLogController {


    @GetMapping("/")
    public AccessLogDto createAccessLog(HttpServletRequest request){

        Map<String, String> Herders = new map<
        String<Method> = request.getMethod();

    }
}
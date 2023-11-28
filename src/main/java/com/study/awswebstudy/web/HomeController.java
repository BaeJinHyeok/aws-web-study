package com.study.awswebstudy.web;

import com.study.awswebstudy.web.dto.AccessLogDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    @GetMapping("/")
    public AccessLogDto createAccessLog(HttpServletRequest request){

    }
}